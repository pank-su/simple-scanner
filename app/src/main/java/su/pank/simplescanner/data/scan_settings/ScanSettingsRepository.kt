package su.pank.simplescanner.data.scan_settings

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import su.pank.simplescanner.data.models.ScanExtension
import su.pank.simplescanner.data.models.ScansSettings
import su.pank.simplescanner.data.models.toExternal
import su.pank.simplescanner.proto.ScansSettingsProto

import su.pank.simplescanner.proto.copy
import javax.inject.Inject

class ScanSettingsRepository @Inject constructor(private val scansSettingsDataStore: DataStore<ScansSettingsProto>) {

    val settings: Flow<ScansSettings> = scansSettingsDataStore.data.map { it.toExternal() }

    suspend fun selectExtension(extension: ScanExtension) {
        scansSettingsDataStore.updateData {
            it.copy {
                this.extension = extension.toProto()
            }
        }
    }

    suspend fun setIsExpanded(isExpanded: Boolean) {
        scansSettingsDataStore.updateData {
            it.copy {
                this.expanded = isExpanded
            }
        }
    }

}


