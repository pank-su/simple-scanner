package su.pank.simplescanner.data.scans

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.data.models.toExternal
import su.pank.simplescanner.proto.Scans
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


/**
 * Сохранённые сканы
 */
class ScansRepository @Inject constructor(private val scansDataStore: DataStore<Scans>) {

    @OptIn(ExperimentalTime::class)
    val scans = scansDataStore.data.map {
        it.scansList.mapNotNull { it.toExternal() }
    }

    @OptIn(ExperimentalUuidApi::class)
    suspend fun getScanById(id: Uuid): ScannedItem {
        return scans.first().first{ it.id == id}
    }

    suspend fun saveScan(item: ScannedItem) {
        scansDataStore.updateData {
            it.toBuilder().addScans(item.toProtoModel()).build()
        }

    }

}


