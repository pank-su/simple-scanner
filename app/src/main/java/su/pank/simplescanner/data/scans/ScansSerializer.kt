package su.pank.simplescanner.data.scans

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import su.pank.simplescanner.proto.Scans
import java.io.InputStream
import java.io.OutputStream

object ScansSerializer : Serializer<Scans> {
    override val defaultValue: Scans = Scans.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Scans {
        try {
            return Scans.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: Scans,
        output: OutputStream
    ) = t.writeTo(output)
}

