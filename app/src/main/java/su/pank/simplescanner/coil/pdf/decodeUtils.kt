package su.pank.simplescanner.coil.pdf

import android.util.Log
import coil3.decode.DecodeUtils
import okio.BufferedSource
import okio.ByteString.Companion.encodeUtf8

private val magicBytes = "%PDF".encodeUtf8()

// TODO: need tests
fun DecodeUtils.isPdf(source: BufferedSource): Boolean{
    Log.d("SHIT", "SHIT HERE")
    return source.rangeEquals(0, magicBytes)
}