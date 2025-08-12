package su.pank.simplescanner.coil.pdf

import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.core.graphics.createBitmap
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.asImage
import coil3.decode.DecodeResult
import coil3.decode.DecodeUtils
import coil3.decode.Decoder
import coil3.decode.ImageSource
import coil3.fetch.SourceFetchResult
import coil3.request.Options
import coil3.request.maxBitmapSize
import coil3.util.component1
import coil3.util.component2
import kotlin.math.roundToInt

class PdfDecoder(private val source: ImageSource, private val options: Options) : Decoder {
    @OptIn(ExperimentalCoilApi::class)
    override suspend fun decode(): DecodeResult? {
        val parcelFd =
            ParcelFileDescriptor.open(source.file().toFile(), ParcelFileDescriptor.MODE_READ_ONLY)

        PdfRenderer(parcelFd).use { renderer ->
            val pageIndex = options.pdfPageIndex
            renderer.openPage(pageIndex).use { page ->
                val pdfWidth = page.width
                val pdfHeight = page.height

                val (dstWidth, dstHeight) = DecodeUtils.computeDstSize(
                    srcWidth = pdfWidth,
                    srcHeight = pdfHeight,
                    targetSize = options.size,
                    scale = options.scale,
                    maxSize = options.maxBitmapSize,
                )
                val multiplier = DecodeUtils.computeSizeMultiplier(
                    srcWidth = pdfWidth,
                    srcHeight = pdfHeight,
                    dstWidth = dstWidth,
                    dstHeight = dstHeight,
                    scale = options.scale,
                )
                val (bitmapWidth, bitmapHeight) = Pair(
                    pdfWidth * multiplier,
                    pdfHeight * multiplier
                )
                val bitmap = createBitmap(bitmapWidth.roundToInt(), bitmapHeight.roundToInt())
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

                val isSampled = (dstWidth < pdfWidth || dstHeight < pdfHeight)
                return DecodeResult(image = bitmap.asImage(), isSampled = isSampled)
            }
        }
    }

    class Factory : Decoder.Factory {
        override fun create(
            result: SourceFetchResult,
            options: Options,
            imageLoader: ImageLoader
        ): Decoder? {
            if (!isApplicable(result)) return null
            return PdfDecoder(result.source, options)
        }

        private fun isApplicable(result: SourceFetchResult): Boolean {
            return result.mimeType == "application/pdf" || DecodeUtils.isPdf(result.source.source())
        }

    }
}