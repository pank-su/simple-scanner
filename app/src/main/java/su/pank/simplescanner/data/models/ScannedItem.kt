package su.pank.simplescanner.data.models

import androidx.core.net.toUri
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import su.pank.simplescanner.R
import su.pank.simplescanner.proto.Extension
import su.pank.simplescanner.proto.Scanned
import su.pank.simplescanner.proto.scanned
import su.pank.simplescanner.proto.scansSettings
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
@OptIn(ExperimentalTime::class)
sealed interface ScannedItem {
    val name: String
    val savedAt: Instant


    fun toProtoModel(): Scanned

    @SerialName("pdf")
    @Serializable
    data class PdfFile(
        override val name: String,
        val file: String,
        val pages: Int,
        override val savedAt: Instant = Clock.System.now(), // ignore error
    ) : ScannedItem {


        override fun toProtoModel(): Scanned {

            return scanned {
                this.name = this@PdfFile.name
                savedAsMs = savedAt.toEpochMilliseconds()
                fileNames.add(file)
                scanSettings = scansSettings {
                    extension = Extension.PDF
                }
            }
        }
    }

    @SerialName("jpg")
    @Serializable
    data class JpgItem(
        override val name: String,
        val files: List<String>,
        override val savedAt: Instant = Clock.System.now(), // ignore error
    ) : ScannedItem {


        override fun toProtoModel(): Scanned {
            return scanned {
                this.name = this@JpgItem.name
                savedAsMs = savedAt.toEpochMilliseconds()
                fileNames.addAll(files.map { it })
                pages = files.size
                scanSettings = scansSettings {
                    extension = Extension.JPG
                }
            }

        }

    }
}


@OptIn(ExperimentalTime::class)
val TestItem = ScannedItem.JpgItem(
    "Test ${Random.nextInt(1, 100)}",
    listOf(
        ("android.resource://su.pank.simplescanner/" + R.drawable.photo).toUri().toString(),
        ("android.resource://su.pank.simplescanner/" + R.drawable.photo).toUri().toString(),
        ("android.resource://su.pank.simplescanner/" + R.drawable.photo).toUri().toString(),
        ("android.resource://su.pank.simplescanner/" + R.drawable.photo).toUri().toString()
    )
)