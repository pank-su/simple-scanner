package su.pank.simplescanner.data.settings

import android.util.Log
import androidx.datastore.core.DataStore
import su.pank.simplescanner.proto.Extension

import su.pank.simplescanner.proto.Settings
import su.pank.simplescanner.proto.copy
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val settingsDataStore: DataStore<Settings>) {
    private val tag = "UserPreferences"
    val userPreferences = settingsDataStore.data

    private suspend fun catch(block: suspend () -> Unit) {
        try {
            block()
        } catch (e: Exception) {
            Log.d(tag, "Failed to update Settings:" + e.stackTraceToString())
        }
    }

    suspend fun selectExtension(extension: Extension) {
        catch {
            settingsDataStore.updateData {
                it.copy {
                    scanSettings = this.scanSettings.copy { this.extension = extension }
                }
            }
        }
    }

    suspend fun expandAppSettings(expanded: Boolean) {
        catch {
            settingsDataStore.updateData {
                it.copy {
                    appSettings = appSettings.copy {
                        this.expanded = expanded
                    }
                }
            }
        }
    }

    suspend fun expandScanSettings(expanded: Boolean) {
        catch {
            settingsDataStore.updateData {
                it.copy {
                    scanSettings = scanSettings.copy {
                        this.expanded = expanded
                    }
                }
            }
        }
    }


    suspend fun googleApiChecked() {
        catch {
            settingsDataStore.updateData {
                it.copy {
                    this.hasGoogleAPI = true
                }
            }
        }
    }
}

