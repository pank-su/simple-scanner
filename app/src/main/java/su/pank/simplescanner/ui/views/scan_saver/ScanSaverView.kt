package su.pank.simplescanner.ui.views.scan_saver

import android.net.Uri
import android.os.Parcel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


@Serializable
data class ScanSaver(val result: GmsDocumentScanningResult)


@Serializable
@SerialName("GmsDocumentScanningResult")
private class GmsDocumentScanningResultSurrogate(
    @Contextual val pages: List<GmsDocumentScanningResult.Page>,
    val pdf: GmsDocumentScanningResult.Pdf
)

@Serializable
@SerialName("Page")
private class PageSurrogate(@Serializable(UriSerializer::class) val image: Uri)

@Serializable
@SerialName("Pdf")
private class PDFSurrogate(@Serializable(UriSerializer::class) val uri: Uri, val pageCount: Int)


object PDFSerializer: KSerializer<GmsDocumentScanningResult.Pdf>{
    override val descriptor: SerialDescriptor = SerialDescriptor(
    "com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult.Pdf",
        PDFSurrogate.serializer().descriptor
    )

    override fun serialize(
        encoder: Encoder,
        value: GmsDocumentScanningResult.Pdf
    ) {
        encoder.encodeSerializableValue(PDFSurrogate.serializer(), PDFSurrogate(uri = value.uri, pageCount = value.pageCount))
    }

    override fun deserialize(decoder: Decoder): GmsDocumentScanningResult.Pdf {
        val surrogate = decoder.decodeSerializableValue(PDFSurrogate.serializer())
        return object : GmsDocumentScanningResult.Pdf() {
            override fun getPageCount(): Int = surrogate.pageCount

            override fun getUri(): Uri =
                surrogate.uri

            override fun describeContents(): Int {
                TODO("Not yet implemented")
            }

            override fun writeToParcel(p0: Parcel, p1: Int) {
                TODO("Not yet implemented")
            }
        }
    }

}

object UriSerializer : KSerializer<Uri> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("android.net.Uri", PrimitiveKind.STRING)

    override fun serialize(
        encoder: Encoder,
        value: Uri
    ) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): Uri =
        decoder.decodeString().toUri()
}



object PageSerializer : KSerializer<GmsDocumentScanningResult.Page> {
    override val descriptor: SerialDescriptor = SerialDescriptor(
        "com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult.Page",
        PageSurrogate.serializer().descriptor
    )

    override fun serialize(
        encoder: Encoder,
        value: GmsDocumentScanningResult.Page
    ) {
        encoder.encodeSerializableValue(PageSurrogate.serializer(), PageSurrogate(value.imageUri))
    }

    override fun deserialize(decoder: Decoder): GmsDocumentScanningResult.Page {
        val surrogate = decoder.decodeSerializableValue(PageSurrogate.serializer())
        return object : GmsDocumentScanningResult.Page() {
            override fun getImageUri(): Uri =
                surrogate.image


            override fun describeContents(): Int {
                TODO("Not yet implemented")
            }

            override fun writeToParcel(p0: Parcel, p1: Int) {
                TODO("Not yet implemented")
            }

        }
    }


}

object GmsDocumentScanningResultSerializer : KSerializer<GmsDocumentScanningResult> {
    override val descriptor: SerialDescriptor = SerialDescriptor(
        "com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult",
        PDFSurrogate.serializer().descriptor
    )

    override fun serialize(
        encoder: Encoder,
        value: GmsDocumentScanningResult
    ) {
        TODO("Not yet implemented")
    }

    override fun deserialize(decoder: Decoder): GmsDocumentScanningResult {
        TODO("Not yet implemented")
    }

}

@Composable
fun ScanSaverView(modifier: Modifier = Modifier) {


}