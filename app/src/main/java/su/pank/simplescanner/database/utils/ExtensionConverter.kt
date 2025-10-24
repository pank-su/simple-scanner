package su.pank.simplescanner.database.utils

import androidx.room.TypeConverter
import su.pank.simplescanner.data.models.ScanExtension

class ExtensionConverter {
    @TypeConverter
    fun toExtension(value: Int) = enumValues<ScanExtension>()[value]

    @TypeConverter
    fun fromExtension(value: ScanExtension) = value.ordinal

}