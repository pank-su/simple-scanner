package su.pank.simplescanner.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import su.pank.simplescanner.database.dao.ScanDao
import su.pank.simplescanner.database.model.ScanEntity
import su.pank.simplescanner.database.utils.ExtensionConverter
import su.pank.simplescanner.database.utils.InstantConverter
import su.pank.simplescanner.database.utils.ListStringConverter

@Database(version = 1, entities = [ScanEntity::class])
@TypeConverters(InstantConverter::class, ListStringConverter::class, ExtensionConverter::class)
abstract class ScannerDatabase : RoomDatabase() {
    abstract fun scanDao(): ScanDao
}