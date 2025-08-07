package su.pank.simplescanner.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import su.pank.simplescanner.data.preferences.UserPreferencesSerializer
import su.pank.simplescanner.data.scans.ScansSerializer
import su.pank.simplescanner.proto.Scans
import su.pank.simplescanner.proto.UserPreferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object dataModule {

    @Provides
    @Singleton
    fun provideUserPreferencesDataStore(@ApplicationContext context: Context): DataStore<UserPreferences> = DataStoreFactory.create(
        serializer = UserPreferencesSerializer,
        scope = CoroutineScope(Dispatchers.IO)
    ) {
        context.dataStoreFile("user_preferences.pb")
    }

    @Provides
    @Singleton
    fun provideScansDataStore(
        @ApplicationContext context: Context
    ): DataStore<Scans> = DataStoreFactory.create(
        serializer = ScansSerializer,
        scope = CoroutineScope(Dispatchers.IO)
    ) {
        context.dataStoreFile("scans.pb")
    }
}