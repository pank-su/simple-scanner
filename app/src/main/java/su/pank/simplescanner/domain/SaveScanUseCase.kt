package su.pank.simplescanner.domain

import android.content.Context
import androidx.core.net.toFile
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import su.pank.simplescanner.data.models.Scan
import su.pank.simplescanner.data.scans.ScanRepository
import su.pank.simplescanner.domain.models.SaveScanTask
import java.io.File
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class SaveScanUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val scanRepository: ScanRepository
) {
    // Now save in the app storage
    // FUTURE: add save to storage in specific path
    @OptIn(ExperimentalTime::class)
    suspend operator fun invoke(saveScanTask: SaveScanTask) {
        val scan = scanRepository.getScanById(saveScanTask.scanId)


        when (scan) {
            is Scan.ScanJpg -> {
                val files = saveLocal(
                    "${scan.name}.jpg",
                    saveScanTask.files,
                    scan.path
                )
                scanRepository.saveScan(scan.copy(fileNames = files))
                coroutineScope {
                    launch {
                        scan.fileNames.map {
                            it.toUri().toFile().delete()
                        } // Remove file from cache
                    }
                }
            }

            is Scan.ScanPdf -> {
                val file = saveLocal(
                    "${scan.name}.pdf",
                    saveScanTask.files.first(),
                    scan.path
                )
                scanRepository.saveScan(scan.copy(fileName = file))
                coroutineScope {
                    launch {
                        scan.fileName.toUri().toFile().delete() // Remove file from cache
                    }
                }

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
        path: String = Uuid.random().toHexDashString()
    ): String {

        val dir = File(path)

        if (!dir.exists()) dir.mkdirs()
        val filePath = dir.resolve(fileName)
        filePath.outputStream().use {
            it.write(fileUri.toUri().toFile().readBytes())
        }

        return filePath.name
    }
}