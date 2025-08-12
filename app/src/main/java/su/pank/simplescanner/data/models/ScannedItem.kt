package su.pank.simplescanner.data.models

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import su.pank.simplescanner.R
import su.pank.simplescanner.proto.Extension
import su.pank.simplescanner.proto.Scanned
import su.pank.simplescanner.proto.scanned
import su.pank.simplescanner.proto.scansSettings
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.Duration.Companion.seconds
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


    override fun toProtoModel(): Scanned = scanned { }

}


