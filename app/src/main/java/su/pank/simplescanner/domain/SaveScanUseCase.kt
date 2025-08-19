package su.pank.simplescanner.domain

import android.content.Context
import androidx.core.net.toFile
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.data.scans.ScansRepository
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class SaveScanUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val scansRepository: ScansRepository
) {
    // Now save in the app storage
    // FUTURE: add save to storage in specific path
    @OptIn(ExperimentalTime::class)
    suspend operator fun invoke(scannedItem: ScannedItem) {
        when (scannedItem) {
            is ScannedItem.JpgItem -> {
                val files = saveLocal("${scannedItem.name}", scannedItem.files, scannedItem.id.toHexString())
                scansRepository.saveScan(scannedItem.copy(files = files))
            }

            is ScannedItem.PdfFile -> {
                val file = saveLocal("${scannedItem.name}", scannedItem.file, scannedItem.id.toHexString())
                scansRepository.saveScan(scannedItem.copy(file = file))
            }
        }
    }

    suspend fun saveLocal(
        fileName: String, fileUris: List<String>, dirName: String = Uuid.random().toHexDashString()
    ): List<String> {
        return buildList {
            fileUris.forEachIndexed { index, uri ->
                add(
                    saveLocal("$index-$fileName", uri, dirName)
                )
            }
        }
    }

    /**
     * @param fileName name of scanned file
     * @param pdfUri uri of pdf/jpg file
     */
    suspend fun saveLocal(
        fileName: String,
        fileUri: String,
        dirName: String = Uuid.random().toHexDashString()
    ): String {

        val dir = context.getDir(dirName, Context.MODE_PRIVATE)

        if (!dir.exists()) dir.mkdirs()
        val filePath = dir.resolve(fileName)
        filePath.outputStream().use {
            it.write(fileUri.toUri().toFile().readBytes())
        }

        return filePath.path
    }
}