package su.pank.simplescanner.data.models

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import su.pank.simplescanner.R
import su.pank.simplescanner.proto.Scanned
import su.pank.simplescanner.proto.scanned
import java.io.File
import java.util.Locale
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
sealed interface ScannedItem {
    val name: String
    val savedAt: Instant
    val preview: ImageBitmap


    fun toProtoModel(): Scanned

    data class PdfFile(
        override val name: String,
        override val savedAt: Instant,
        val file: File
    ) : ScannedItem {
        override val preview: ImageBitmap
            get() = TODO()


        override fun toProtoModel(): Scanned {

            return scanned {
                this.name = this@PdfFile.name
                savedAsMs = savedAt.toEpochMilliseconds()
                fileNames.add(file.name)
            }
        }
    }

    data class JpgItem(
        override val name: String,
        override val savedAt: Instant,
        val files: List<File>
    ) : ScannedItem {
        override val preview: ImageBitmap
            get() = BitmapFactory.decodeFile(files.first().path).asImageBitmap()


        override fun toProtoModel(): Scanned {
            return scanned {
                this.name = this@JpgItem.name
                savedAsMs = savedAt.toEpochMilliseconds()
                fileNames.addAll(files.map { it.name })
            }

        }

    }
}

/**
 * Item for preview and testing
 */
class TestItem(private val context: Context, override val name: String = "Test") : ScannedItem {

    val icon: ImageVector
        @Composable get() {
            return ImageVector.vectorResource(R.drawable.pdf_icon)
        }

    @OptIn(ExperimentalTime::class)
    override val savedAt: Instant = Clock.System.now() - Random.nextInt(1, 30).seconds
    override val preview: ImageBitmap
        get() {
            return ImageBitmap.imageResource(context.resources, R.drawable.photo)
        }

    override fun toProtoModel(): Scanned = scanned { }

}


