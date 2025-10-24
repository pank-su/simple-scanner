package su.pank.simplescanner.ui.views.main

import android.app.Activity
import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.core.net.toFile
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.json.Json
import su.pank.simplescanner.data.models.NewScan
import su.pank.simplescanner.data.models.ScanExtension
import su.pank.simplescanner.data.scan_settings.ScanSettingsRepository
import su.pank.simplescanner.data.scans.ScanRepository
import su.pank.simplescanner.domain.ScanNameUseCase
import su.pank.simplescanner.domain.models.SaveScanTask
import su.pank.simplescanner.ui.components.ScansUiState
import su.pank.simplescanner.utils.timeFlow
import su.pank.simplescanner.work.SaveScanWorker
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi


@OptIn(ExperimentalTime::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val scansRepository: ScanRepository,
    private val scanSettingsRepository: ScanSettingsRepository,
    private val scanNameUseCase: ScanNameUseCase
) :
    ViewModel() {

    val scansUiState = combine(scansRepository.scans, timeFlow(1.seconds)) { scans, time ->
        if (scans.isEmpty()) {
            return@combine ScansUiState.Empty
        }
        ScansUiState.Success(scans.sortedByDescending { it.savedAt }, time)
    }.catch { ScansUiState.Error }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ScansUiState.Loading
    )


    fun scan(
        activity: Activity,
        launcher: ActivityResultLauncher<IntentSenderRequest>
    ) {
        viewModelScope.launch {
            val extension = scanSettingsRepository.settings.first().extension

            val options =
                GmsDocumentScannerOptions.Builder().setGalleryImportAllowed(true).setScannerMode(
                    GmsDocumentScannerOptions.SCANNER_MODE_FULL
                ).setResultFormats(
                    when (extension) {
                        ScanExtension.PDF -> GmsDocumentScannerOptions.RESULT_FORMAT_PDF
                        ScanExtension.JPEG -> GmsDocumentScannerOptions.RESULT_FORMAT_JPEG
                    }
                ).build()


            val scanner = GmsDocumentScanning.getClient(options)
            try {
                val intent = scanner.getStartScanIntent(activity).await()

                launcher.launch(IntentSenderRequest.Builder(intent).build())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun saveFile(result: GmsDocumentScanningResult, context: Context) {

        viewModelScope.launch(context = Dispatchers.IO) {
            val settings = scanSettingsRepository.settings.first()
            val name = scanNameUseCase()
            // TODO: check settings
            val new: NewScan? = if (result.pdf?.uri != null) {
                NewScan(
                    name,
                    result.pdf?.uri?.toFile()?.parentFile?.canonicalPath!!,
                    result.pdf?.pageCount!!,
                    ScanExtension.PDF
                )
            } else if (result.pages != null) {
                NewScan(
                    name,
                    result.pages?.first()?.imageUri?.toFile()?.parentFile?.canonicalPath!!,
                    result.pages?.size!!,
                    extension = ScanExtension.JPEG
                )
            } else null


            requireNotNull(new)
            val newId = scansRepository.newScan(new)


            val workManager = WorkManager.getInstance(context)
            // Send request to save scan and open file
            workManager.enqueue(
                OneTimeWorkRequestBuilder<SaveScanWorker>().addTag("save_scan_$newId")
                    .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                    .setInputData(
                        workDataOf(
                            "data" to Json.encodeToString(
                                SaveScanTask(
                                    newId,
                                    if (result.pdf?.uri != null) {
                                        listOf(result.pdf!!.uri.toString())
                                    } else {
                                        result.pages!!.map { it.imageUri.toString() }
                                    },
                                )
                            )
                        )
                    )
                    .build()
            )
            //return scansRepository.getScanById(newId)
        }

    }

    fun readLocaleCsv() {

    }

}


