package su.pank.simplescanner.data.models

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.InstantComponentSerializer
import su.pank.simplescanner.R
import su.pank.simplescanner.proto.Extension
import su.pank.simplescanner.proto.Scanned
import su.pank.simplescanner.proto.ScansSettings
import su.pank.simplescanner.proto.scanned
import su.pank.simplescanner.proto.scansSettings
import java.io.File
import java.net.URI
import java.util.Locale
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
@OptIn(ExperimentalTime::class)
sealed interface ScannedItem {
    val name: String
    val savedAt: Instant
    val preview: ImageBitmap


    fun toProtoModel(): Scanned

    @Serializable
    data class PdfFile(
        override val name: String,
        override val savedAt: Instant, // ignore error
        val file: String
    ) : ScannedItem {
        override val preview: ImageBitmap
            get() = TODO()


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

    @Serializable
    data class JpgItem(
        override val name: String,
        override val savedAt: Instant,
        val files: List<String>
    ) : ScannedItem {
        override val preview: ImageBitmap
            get() = BitmapFactory.decodeFile(files.first()).asImageBitmap()


        override fun toProtoModel(): Scanned {
            return scanned {
                this.name = this@JpgItem.name
                savedAsMs = savedAt.toEpochMilliseconds()
                fileNames.addAll(files.map { it })
                scanSettings = scansSettings {
                    extension = Extension.JPG
                }
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


