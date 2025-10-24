package su.pank.simplescanner.data.scans

import kotlinx.coroutines.flow.Flow
import su.pank.simplescanner.data.models.NewScan
import su.pank.simplescanner.data.models.Scan


interface ScanRepository {
    val scans: Flow<List<Scan>>

    suspend fun getScanById(id: Int): Scan

    suspend fun saveScan(scan: Scan)

    suspend fun newScan(newScan: NewScan): Int
}