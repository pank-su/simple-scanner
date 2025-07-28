package su.pank.simplescanner.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.map
import su.pank.simplescanner.proto.Scans
import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

val Context.scans: DataStore<Scans> by dataStore(
    fileName = "scans.pb",
    serializer = ScansSerializer
)


@OptIn(ExperimentalTime::class)
sealed interface Scanned {
    val name: String
    val savedAt: Instant

    fun toProtoModel(): su.pank.simplescanner.proto.Scanned

    data class PdfFile(
        override val name: String,
        override val savedAt: Instant,
        val file: File
    ) : Scanned {
        // TODO: сделать метод более простым и вынести
        override fun toProtoModel(): su.pank.simplescanner.proto.Scanned {
            return su.pank.simplescanner.proto.Scanned.newBuilder().setName(name)
                .setSavedAsMs(savedAt.toEpochMilliseconds()).addFileNames(file.name).build()
        }
    }

    data class JpgFile(
        override val name: String,
        override val savedAt: Instant,
        val files: List<File>
    ) : Scanned {
        override fun toProtoModel(): su.pank.simplescanner.proto.Scanned {
            return su.pank.simplescanner.proto.Scanned.newBuilder().setName(name)
                .setSavedAsMs(savedAt.toEpochMilliseconds()).addAllFileNames(files.map { it.name })
                .build()

        }

    }
}

/**
 * Сохранённые сканы
 */
// FUTURE: пока чисто разметил полупсевдокодом, надо при реализации подправить эти файлы
class ScansRepository(private val context: Context) {

    val scans = context.scans.data.map {
        it.scansList.mapNotNull { it.toDataModel() }
    }

    suspend fun addScan(scanned: Scanned) {

        context.scans.updateData {
            it.toBuilder().addScans(scanned.toProtoModel()).build()
        }

    }

}

@OptIn(ExperimentalTime::class)
private fun su.pank.simplescanner.proto.Scanned.toDataModel(): Scanned? {
    return when (this.extension.name) {
        "PDF" -> {
            Scanned.PdfFile(
                name,
                Instant.fromEpochMilliseconds(savedAsMs),
                File(fileNamesList.first())
            )
        }

        "JPG" -> {
            Scanned.JpgFile(
                name,
                Instant.fromEpochMilliseconds(savedAsMs),
                fileNamesList.map { File(it) })
        }

        else -> {
            null
        }
    }
}
