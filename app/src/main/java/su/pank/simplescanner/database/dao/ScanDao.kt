package su.pank.simplescanner.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import su.pank.simplescanner.data.models.NewScan
import su.pank.simplescanner.database.model.ScanEntity

@Dao
interface ScanDao {

    @Upsert
    fun upsertScan(scan: ScanEntity)

    @Insert(entity = ScanEntity::class)
    fun insertNewScan(newScan: NewScan): Long

    @Query("SELECT * FROM scans ORDER BY savedAt DESC")
    fun all(): Flow<List<ScanEntity>>

    @Query("SELECT * FROM scans WHERE id = :id")
    fun byId(id: Int): ScanEntity

    @Query("SELECT id FROM scans WHERE rowId = :rowId")
    fun byRowId(rowId: Long): Int

}


