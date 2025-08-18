package su.pank.simplescanner.data.scan_settings

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import su.pank.simplescanner.proto.ScansSettingsProto
import java.io.InputStream
import java.io.OutputStream

object ScanSettingsSerializer : Serializer<ScansSettingsProto> {
    override val defaultValue: ScansSettingsProto = ScansSettingsProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ScansSettingsProto {
        try {
            return ScansSettingsProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: ScansSettingsProto,
        output: OutputStream
    ) = t.writeTo(output)
}