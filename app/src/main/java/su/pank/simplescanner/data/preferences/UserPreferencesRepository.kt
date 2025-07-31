package su.pank.simplescanner.data.preferences

import android.util.Log
import androidx.datastore.core.DataStore
import su.pank.simplescanner.proto.UserPreferences
import su.pank.simplescanner.proto.copy
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(private val userPreferencesDataStore: DataStore<UserPreferences>) {
    private val tag = "UserPreferences"
    val userPreferences = userPreferencesDataStore.data

    suspend fun setDarkTheme(isDark: Boolean){
        try {
            userPreferencesDataStore.updateData { it.copy {
                this.isDarkTheme = isDark
            } }
        } catch (e: Exception){
            Log.d(tag, "Failed to update UserPreferences:" + e.stackTraceToString())
        }
    }

    suspend fun setDynamicTheme(isDynamic: Boolean){
        try {
            userPreferencesDataStore.updateData { it.copy {
                this.isDynamicTheme = isDynamic
            } }
        } catch (e: Exception){
            Log.d(tag, "Failed to update UserPreferences:" + e.stackTraceToString())
        }
    }

    suspend fun googleApiChecked(){
        try {
            userPreferencesDataStore.updateData { it.copy {
                this.hasGoogleAPI = true
            } }
        } catch (e: Exception){
            Log.d(tag, "Failed to update UserPreferences:" + e.stackTraceToString())
        }
    }
}