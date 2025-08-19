package su.pank.simplescanner.ui.views.scan

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import androidx.work.WorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.data.scans.ScansRepository
import su.pank.simplescanner.work.SaveScanWorker
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.toJavaUuid

@OptIn(ExperimentalUuidApi::class)
@HiltViewModel
class ScanViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @ApplicationContext context: Context,
    scansRepository: ScansRepository
) : ViewModel() {
    private val scanItemFromHandle: ScannedItem = Scan.from(savedStateHandle).scannedItem
    val scanItem: Flow<ScannedItem> =
        WorkManager.getInstance(context).getWorkInfoByIdFlow(scanItemFromHandle.id.toJavaUuid())
            .map {
                if (it?.state != WorkInfo.State.SUCCEEDED) return@map scanItemFromHandle
                return@map scansRepository.getScanById(scanItemFromHandle.id)
            }



}

