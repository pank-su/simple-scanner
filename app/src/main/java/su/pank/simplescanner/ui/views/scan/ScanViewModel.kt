package su.pank.simplescanner.ui.views.scan

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import androidx.work.WorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.data.scans.ScansRepository
import java.io.File
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
    val scanItem =
        WorkManager.getInstance(context).getWorkInfoByIdFlow(scanItemFromHandle.id.toJavaUuid())
            .map {
                if (it?.state != WorkInfo.State.SUCCEEDED) return@map scanItemFromHandle
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
                is ScannedItem.JpgItem -> {
                    Intent().apply {
                        action = Intent.ACTION_SEND_MULTIPLE
                        putParcelableArrayListExtra(
                            Intent.EXTRA_STREAM,
                            ArrayList(item.files.map { getUri(context, it) })
                        )

                        type = "image/*"
                    }
                }

                is ScannedItem.PdfFile -> Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_STREAM, getUri(context, item.file))
                    type = "application/pdf"
                }
            }.apply {
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
        context.startActivity(Intent.createChooser(intent, null))

    }


}

