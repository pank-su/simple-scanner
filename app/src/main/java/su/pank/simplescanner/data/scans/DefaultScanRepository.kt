package su.pank.simplescanner.data.scans

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import su.pank.simplescanner.data.models.NewScan
import su.pank.simplescanner.data.models.Scan
import su.pank.simplescanner.data.models.ScanExtension
import su.pank.simplescanner.database.dao.ScanDao
import su.pank.simplescanner.database.model.ScanEntity
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class DefaultScanRepository @Inject constructor(val scanDao: ScanDao) : ScanRepository {
    override val scans: Flow<List<Scan>> = scanDao.all().map { it.toExternal() }

    override suspend fun getScanById(id: Int): Scan =
        scanDao.byId(id).toExternal()


    override suspend fun saveScan(scan: Scan) {
        scanDao.upsertScan(scan.toEntity())
    }

    /**
     * @return id new scan
     */
    override suspend fun newScan(newScan: NewScan): Int =
        scanDao.byRowId(scanDao.insertNewScan(newScan))

}

@OptIn(ExperimentalTime::class)
private fun Scan.toEntity(): ScanEntity = when (this) {
    is Scan.ScanJpg -> ScanEntity(
        id,
        name,
        path,
        fileNames,
        savedAt,
        fileNames.size,
        ScanExtension.JPEG
    )

    is Scan.ScanPdf -> ScanEntity(
        id,
        name,
        path,
        listOf(fileName),
        savedAt,
        pages,
        ScanExtension.PDF
    )
}

private fun List<ScanEntity>.toExternal(): List<Scan> {
    return map { it.toExternal() }
}

@OptIn(ExperimentalTime::class)
private fun ScanEntity.toExternal(): Scan =
    when (extension) {
        ScanExtension.PDF -> Scan.ScanPdf(
            id,
            name,
            path,
            fileNames.first(),
            pages,
            savedAt = savedAt
        )

        ScanExtension.JPEG -> Scan.ScanJpg(id, name, path, fileNames, savedAt)
    }

