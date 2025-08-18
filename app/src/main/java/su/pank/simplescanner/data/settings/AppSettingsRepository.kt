package su.pank.simplescanner.data.settings

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.map
import su.pank.simplescanner.data.models.AppSettings
import su.pank.simplescanner.proto.AppSettingsProto
import su.pank.simplescanner.proto.copy
import javax.inject.Inject

class AppSettingsRepository @Inject constructor(private val settingsDataStore: DataStore<AppSettingsProto>) {
    val settings = settingsDataStore.data.map { it.toExternal() }


    suspend fun googleApiChecked() {
        settingsDataStore.updateData {
            it.copy {
                this.hasGoogleAPI = true
            }
        }

    }
}

private fun AppSettingsProto.toExternal(): AppSettings = AppSettings(hasGoogleAPI)

