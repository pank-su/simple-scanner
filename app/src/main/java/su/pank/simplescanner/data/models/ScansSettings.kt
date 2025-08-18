package su.pank.simplescanner.data.models

import su.pank.simplescanner.proto.ScanExtensionProto


data class ScansSettings(val extension: ScanExtension)


enum class ScanExtension {

    PDF {
        override fun toProto(): ScanExtensionProto = ScanExtensionProto.PDF
    },
    JPEG {
        override fun toProto(): ScanExtensionProto = ScanExtensionProto.JPG
    };

    abstract fun toProto(): ScanExtensionProto
}