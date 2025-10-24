package su.pank.simplescanner.data.models

import android.content.Context
import coil3.request.ImageRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import su.pank.simplescanner.R
import su.pank.simplescanner.coil.pdf.pdfPageIndex
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi

@Serializable
@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
sealed interface Scan {
    val id: Int
    val name: String
    val savedAt: Instant
    val path: String

    fun imageRequests(context: Context): List<ImageRequest.Builder>



    @SerialName("pdf")
    @Serializable
    data class ScanPdf(
        override val id: Int,
        override val name: String,
        override val path: String,
        val file: String,
        val pages: Int,
        override val savedAt: Instant = Clock.System.now(),         // ignore error

    ) : Scan {


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



    }

    @SerialName("jpg")
    @Serializable
    data class ScanJpg(
        override val id: Int,
        override val name: String,
        override val path: String,
        val files: List<String>,
        override val savedAt: Instant = Clock.System.now(), // ignore error
    ) : Scan {
        override fun imageRequests(context: Context) = files.mapIndexed { index, file ->
            ImageRequest.Builder(context).data(file)
                .placeholderMemoryCacheKey(id.toHexString() + "$index")
                .memoryCacheKey(id.toHexString() + "$index")

        }



    }
}

/**
 * Скан без id и файлов для вставки
 */
@Serializable
data class NewScan(
    val name: String,
    val path: String,
    val pages: Int,
    val extension: ScanExtension,
    val savedAt: Instant = Clock.System.now()

)

@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
val testScan = Scan.ScanJpg(
    id = Random.nextInt(),
    "Test ${Random.nextInt(1, 100)}",
    "android.resource://su.pank.simplescanner/",
    listOf(
        R.drawable.ic_launcher_foreground.toString(),
    ),
)