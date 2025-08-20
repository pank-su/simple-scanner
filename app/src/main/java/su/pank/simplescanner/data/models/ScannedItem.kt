package su.pank.simplescanner.data.models

import android.content.Context
import androidx.core.net.toUri
import coil3.request.ImageRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import su.pank.simplescanner.R
import su.pank.simplescanner.coil.pdf.pdfPageIndex
import su.pank.simplescanner.proto.ScanExtensionProto
import su.pank.simplescanner.proto.Scanned
import su.pank.simplescanner.proto.copy
import su.pank.simplescanner.proto.scanned
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
sealed interface ScannedItem {
    val id: Uuid
    val name: String
    val savedAt: Instant
    val scannedSettings: ScansSettings

    fun imageRequests(context: Context): List<ImageRequest.Builder>


    fun toProtoModel(): Scanned = scanned {
        this.id = this@ScannedItem.id.toHexString()
        this.name = this@ScannedItem.name
        savedAsMs = savedAt.toEpochMilliseconds()
        this.scanSettings = scannedSettings.toProto()
    }

    @SerialName("pdf")
    @Serializable
    data class PdfFile @OptIn(ExperimentalUuidApi::class) constructor(

        override val name: String,
        val file: String,
        val pages: Int,
        override val scannedSettings: ScansSettings,
        override val id: Uuid = Uuid.random(),
        override val savedAt: Instant = Clock.System.now(),
        // ignore error
    ) : ScannedItem {


        override fun imageRequests(context: Context) = buildList {
            repeat(pages) {
                add(
                    ImageRequest.Builder(context).data(file)
                        .placeholderMemoryCacheKey(id.toHexString() + "$it")
                        .memoryCacheKey(id.toHexString() + "$it")
                        .pdfPageIndex(it)
                )
            }
        }


        override fun toProtoModel(): Scanned {
            return super.toProtoModel().copy {

                this.pages = this@PdfFile.pages
                fileNames.add(file)

            }
        }
    }

    @SerialName("jpg")
    @Serializable
    data class JpgItem @OptIn(ExperimentalUuidApi::class) constructor(
        override val name: String,
        val files: List<String>,
        override val scannedSettings: ScansSettings,
        override val id: Uuid = Uuid.random(),
        override val savedAt: Instant = Clock.System.now(), // ignore error
    ) : ScannedItem {
        override fun imageRequests(context: Context) = files.mapIndexed { index, file ->
            ImageRequest.Builder(context).data(file)
                .placeholderMemoryCacheKey(id.toHexString() + "$index")
                .memoryCacheKey(id.toHexString() + "$index")

        }


        override fun toProtoModel(): Scanned {
            return super.toProtoModel().copy {
                fileNames.addAll(files.map { it })
                pages = files.size

            }

        }

    }
}

@OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)
fun Scanned.toExternal(): ScannedItem? {
    return when (this.scanSettings.extension) {
        ScanExtensionProto.PDF -> {
            ScannedItem.PdfFile(
                name,
                fileNamesList.first(),
                pages, scanSettings.toExternal(), Uuid.parseHex(id),
                Instant.fromEpochMilliseconds(savedAsMs)
            )
        }

        ScanExtensionProto.JPG -> {
            ScannedItem.JpgItem(
                name,
                fileNamesList.map { it }, scanSettings.toExternal(), Uuid.parseHex(id),
                Instant.fromEpochMilliseconds(savedAsMs)
            )
        }

        else -> {
            null
        }
    }
}


@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
val TestItem = ScannedItem.JpgItem(
    "Test ${Random.nextInt(1, 100)}",
    listOf(
        ("android.resource://su.pank.simplescanner/" + R.drawable.photo).toUri().toString(),
        ("android.resource://su.pank.simplescanner/" + R.drawable.photo).toUri().toString(),
        ("android.resource://su.pank.simplescanner/" + R.drawable.photo).toUri().toString(),
        ("android.resource://su.pank.simplescanner/" + R.drawable.photo).toUri().toString()
    ),
    ScansSettings.DEFAULT
)