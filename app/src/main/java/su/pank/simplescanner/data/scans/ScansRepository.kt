package su.pank.simplescanner.data.scans

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.map
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.proto.Scanned
import su.pank.simplescanner.proto.Scans
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


/**
 * Сохранённые сканы
 */
class ScansRepository @Inject constructor(private val scansDataStore: DataStore<Scans>) {

    @OptIn(ExperimentalTime::class)
    val scans = scansDataStore.data.map {
        it.scansList.mapNotNull { it.toDataModel() }.sortedByDescending { it.savedAt }
    }

    suspend fun saveScan(item: ScannedItem) {
        scansDataStore.updateData {
            it.toBuilder().addScans(item.toProtoModel()).build()
        }

    }

}



@OptIn(ExperimentalTime::class)
private fun Scanned.toDataModel(): ScannedItem? {
    return when (this.scanSettings.extension.name) {
        "PDF" -> {
            ScannedItem.PdfFile(
                name,

                fileNamesList.first(),
                Instant.fromEpochMilliseconds(savedAsMs)
            )
        }

        "JPG" -> {
            ScannedItem.JpgItem(
                name,
                fileNamesList.map { it },
                Instant.fromEpochMilliseconds(savedAsMs)
                )
        }

        else -> {
            null
        }
    }
}
