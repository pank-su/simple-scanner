package su.pank.simplescanner.data.scans

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.map
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.proto.Scanned
import su.pank.simplescanner.proto.Scans
import java.io.File
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


/**
 * Сохранённые сканы
 */

class ScansRepository @Inject constructor(private val scansDataStore: DataStore<Scans>) {

    val scans = scansDataStore.data.map {
        it.scansList.mapNotNull { it.toDataModel() }
    }

    suspend fun addScan(scanned: ScannedItem) {

        scansDataStore.updateData {
            it.toBuilder().addScans(scanned.toProtoModel()).build()
        }

    }

}



@OptIn(ExperimentalTime::class)
private fun Scanned.toDataModel(): ScannedItem? {
    return when (this.scanSettings.extension.name) {
        "PDF" -> {
            ScannedItem.PdfFile(
                name,
                Instant.fromEpochMilliseconds(savedAsMs),
                File(fileNamesList.first())
            )
        }

        "JPG" -> {
            ScannedItem.JpgItem(
                name,
                Instant.fromEpochMilliseconds(savedAsMs),
                fileNamesList.map { File(it) })
        }

        else -> {
            null
        }
    }
}
