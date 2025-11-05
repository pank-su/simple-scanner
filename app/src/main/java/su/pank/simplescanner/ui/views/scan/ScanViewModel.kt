package su.pank.simplescanner.ui.views.scan

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import androidx.work.WorkManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import su.pank.simplescanner.data.models.Scan
import su.pank.simplescanner.data.scans.ScanRepository
import java.io.File
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@HiltViewModel(assistedFactory = ScanViewModel.Factory::class)
class ScanViewModel @AssistedInject constructor(
    @ApplicationContext context: Context,
    scansRepository: ScanRepository,
    @Assisted private val scanItemFromHandle: Scan
) : ViewModel() {
    val scanItem =
        WorkManager.getInstance(context).getWorkInfosByTagFlow("save_scan_${scanItemFromHandle.id}")
            .map {
                if (it.firstOrNull()?.state != WorkInfo.State.SUCCEEDED) return@map scanItemFromHandle
                return@map scansRepository.getScanById(scanItemFromHandle.id)
            }.stateIn(viewModelScope, SharingStarted.Eagerly, scanItemFromHandle)


    private fun getUri(context: Context, path: String) = FileProvider.getUriForFile(
        context, context.packageName + ".provider",
        File(path)
    )

    fun shareScan(context: Context) {
        val item = scanItem.value
        val intent =
            when (item) {
                is Scan.ScanJpg -> {
                    Intent().apply {
                        action = Intent.ACTION_SEND_MULTIPLE
                        putParcelableArrayListExtra(
                            Intent.EXTRA_STREAM,
                            ArrayList(item.fileNames.map { getUri(context, it) })
                        )

                        type = "image/*"
                    }
                }

                is Scan.ScanPdf -> Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_STREAM, getUri(context, item.fileName))
                    type = "application/pdf"
                }
            }.apply {
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
        context.startActivity(Intent.createChooser(intent, null))

    }

    @AssistedFactory
    interface Factory {
        fun create(scanItem: Scan): ScanViewModel
    }


}

