package su.pank.simplescanner.coil.pdf

import coil3.Extras
import coil3.getExtra
import coil3.request.ImageRequest
import coil3.request.Options

fun ImageRequest.Builder.pdfPageIndex(pageIndex: Int) = apply {
    require(pageIndex >= 0) { "pageIndex must be >= 0" }
    memoryCacheKeyExtra("coil#pdfPageIndex", pageIndex.toString())
    extras[pdfPageIndexKey] = pageIndex
}

val ImageRequest.pdfPageIndex: Int
    get() = getExtra(pdfPageIndexKey)

val Options.pdfPageIndex: Int
    get() = getExtra(pdfPageIndexKey)

private val pdfPageIndexKey = Extras.Key(default = 0)