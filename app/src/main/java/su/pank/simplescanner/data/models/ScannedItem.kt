package su.pank.simplescanner.data.models

import android.content.Context
import android.util.Log
import androidx.core.net.toUri
import coil3.request.ImageRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import su.pank.simplescanner.R
import su.pank.simplescanner.coil.pdf.pdfPageIndex
import su.pank.simplescanner.proto.ScanExtensionProto
import su.pank.simplescanner.proto.Scanned
import su.pank.simplescanner.proto.scanned
import su.pank.simplescanner.proto.scansSettingsProto
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
@OptIn(ExperimentalTime::class)
sealed interface ScannedItem {
    val name: String
    val savedAt: Instant

    fun imageRequests(context: Context): List<ImageRequest.Builder>


    fun toProtoModel(): Scanned

    @SerialName("pdf")
    @Serializable
    data class PdfFile(
        override val name: String,
        val file: String,
        val pages: Int,
        override val savedAt: Instant = Clock.System.now(), // ignore error
    ) : ScannedItem {


        override fun imageRequests(context: Context) = buildList {
            Log.d("SHIT", file)
            repeat(pages) {
                add(
                    ImageRequest.Builder(context).data(file)
                        .placeholderMemoryCacheKey(file + "$it")
                        .memoryCacheKey(file + "$it")
                        .pdfPageIndex(it)
                )
            }
        }


        override fun toProtoModel(): Scanned {

            return scanned {
                this.name = this@PdfFile.name
                savedAsMs = savedAt.toEpochMilliseconds()
                this.pages = this@PdfFile.pages
                fileNames.add(file)
                scanSettings = scansSettingsProto {
                    extension = ScanExtensionProto.PDF
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
        override fun imageRequests(context: Context) = files.map {
            ImageRequest.Builder(context).data(it)
                .placeholderMemoryCacheKey(it)
                .memoryCacheKey(it)
        }


        override fun toProtoModel(): Scanned {
            return scanned {
                this.name = this@JpgItem.name
                savedAsMs = savedAt.toEpochMilliseconds()
                fileNames.addAll(files.map { it })
                pages = files.size
                scanSettings = scansSettingsProto {
                    extension = ScanExtensionProto.JPG
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