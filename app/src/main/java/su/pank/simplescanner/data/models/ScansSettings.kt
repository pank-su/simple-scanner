package su.pank.simplescanner.data.models

import kotlinx.serialization.Serializable
import su.pank.simplescanner.proto.ScanExtensionProto
import su.pank.simplescanner.proto.ScansSettingsProto
import su.pank.simplescanner.proto.scansSettingsProto

@Serializable
data class ScansSettings(val isExpanded: Boolean, val extension: ScanExtension) {
    companion object {
        val DEFAULT = ScansSettings(false, ScanExtension.PDF)
    }

    fun toProto(): ScansSettingsProto = scansSettingsProto {
        expanded = isExpanded
        this.extension = this@ScansSettings.extension.toProto()
    }

}

@Serializable
enum class ScanExtension {

    PDF {
        override fun toProto(): ScanExtensionProto = ScanExtensionProto.PDF
    },
    JPEG {
        override fun toProto(): ScanExtensionProto = ScanExtensionProto.JPG
    };

    abstract fun toProto(): ScanExtensionProto
}

fun ScansSettingsProto.toExternal(): ScansSettings = ScansSettings(expanded, extension.toExternal())

fun ScanExtensionProto.toExternal(): ScanExtension {
    return when (this) {
        ScanExtensionProto.PDF -> ScanExtension.PDF
        ScanExtensionProto.JPG -> ScanExtension.JPEG
        else -> throw IllegalArgumentException("Unknown extension")
    }

}