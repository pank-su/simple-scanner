package su.pank.simplescanner.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import su.pank.simplescanner.data.models.ScanExtension
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Entity(tableName = "scans")
data class ScanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val path: String,
    @ColumnInfo(defaultValue = "")
    val fileNames: List<String>,
    val savedAt: Instant,
    val pages: Int,
    val extension: ScanExtension
)

