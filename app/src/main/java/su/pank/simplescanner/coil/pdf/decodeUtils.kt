package su.pank.simplescanner.coil.pdf

import coil3.decode.DecodeUtils
import okio.BufferedSource
import okio.ByteString.Companion.encodeUtf8

private val magicBytes = "%PDF".encodeUtf8()

// TODO: need tests
fun DecodeUtils.isPdf(source: BufferedSource): Boolean {
    return source.rangeEquals(0, magicBytes)
}