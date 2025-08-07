package su.pank.simplescanner.data.scans

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.map
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.proto.Scanned
import su.pank.simplescanner.proto.Scans
import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

val Context.scans: DataStore<Scans> by dataStore(
    fileName = "scans.pb",
    serializer = ScansSerializer
)


/**
 * Сохранённые сканы
 */
// FUTURE: пока чисто разметил полупсевдокодом, надо при реализации подправить эти файлы
class ScansRepository(private val context: Context) {

    val scans = context.scans.data.map {
        it.scansList.mapNotNull { it.toDataModel() }
    }

    suspend fun addScan(scanned: ScannedItem) {

        context.scans.updateData {
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
