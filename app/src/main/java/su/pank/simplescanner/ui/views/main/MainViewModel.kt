package su.pank.simplescanner.ui.views.main

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.json.Json
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.data.preferences.UserPreferencesRepository
import su.pank.simplescanner.data.scans.ScansRepository
import su.pank.simplescanner.proto.ScansSettings
import su.pank.simplescanner.ui.components.ScansUiState
import su.pank.simplescanner.work.SaveScanWorker
import javax.inject.Inject
import kotlin.time.Clock
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val scansRepository: ScansRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) :
    ViewModel() {

    val scansUiState = combine(scansRepository.scans, timeFlow()) { scans, time ->
        if (scans.isEmpty()) {
            return@combine ScansUiState.Empty
        }
        Log.d("MainViewModel", "scansUiState: $scans")
        ScansUiState.Success(scans, time)
    }.catch { ScansUiState.Error }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ScansUiState.Loading
    )

    val settingsUiState = userPreferencesRepository.userPreferences.map {
        ScansSettingsUiState.Success(it.scanSettings)
    }.catch { ScansSettingsUiState.Error }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ScansSettingsUiState.Loading
    )

    private fun timeFlow() = flow {
        while (true) {
            emit(Clock.System.now())
            delay(1.seconds)
        }
    }

    fun scan(
        activity: Activity,
        options: GmsDocumentScannerOptions,
        launcher: ActivityResultLauncher<IntentSenderRequest>
    ) {
        viewModelScope.launch {


            val scanner = GmsDocumentScanning.getClient(options)
            try {
                val intent = scanner.getStartScanIntent(activity).await()

                launcher.launch(IntentSenderRequest.Builder(intent).build())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun saveFile(result: GmsDocumentScanningResult, context: Context): ScannedItem {
        val file: ScannedItem? = if (result.pdf?.uri != null) {
            ScannedItem.PdfFile("Test", result.pdf!!.uri.toString(), result.pdf?.pageCount!!)
        } else if (result.pages != null) {
            ScannedItem.JpgItem("Test", result.pages!!.map { it.imageUri.toString() })
        } else null

        requireNotNull(file)
        val workManager = WorkManager.getInstance(context)
        // Send request to save scan and open file
        workManager.enqueue(
            OneTimeWorkRequestBuilder<SaveScanWorker>()
                .setInputData(
                    workDataOf(
                        "data" to Json.encodeToString(file)
                    )
                )
                .build()
        )
        return file

    }

}


sealed interface ScansSettingsUiState {
    object Loading : ScansSettingsUiState
    object Error : ScansSettingsUiState
    data class Success(val settings: ScansSettings) : ScansSettingsUiState
}