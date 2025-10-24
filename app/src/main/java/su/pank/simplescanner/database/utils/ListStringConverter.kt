package su.pank.simplescanner.database.utils

import androidx.room.TypeConverter

class ListStringConverter {
    @TypeConverter
    fun toString(list: List<String>): String =
        list.joinToString(",")


    @TypeConverter
    fun toList(str: String): List<String> =
        str.split(",")
}