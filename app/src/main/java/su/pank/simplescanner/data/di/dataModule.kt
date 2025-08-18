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
import su.pank.simplescanner.data.scan_settings.ScanSettingsSerializer
import su.pank.simplescanner.data.scans.ScansSerializer
import su.pank.simplescanner.data.settings.SettingsSerializer
import su.pank.simplescanner.proto.AppSettingsProto
import su.pank.simplescanner.proto.Scans
import su.pank.simplescanner.proto.ScansSettingsProto

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object dataModule {

    @Provides
    @Singleton
    fun provideAppSettingsDataStore(@ApplicationContext context: Context): DataStore<AppSettingsProto> =
        DataStoreFactory.create(
            serializer = SettingsSerializer,
            scope = CoroutineScope(Dispatchers.IO)
        ) {
            context.dataStoreFile("settings.pb")
        }

    @Provides
    @Singleton
    fun provideScansSettingsDataStore(@ApplicationContext context: Context): DataStore<ScansSettingsProto> =
        DataStoreFactory.create(
            serializer = ScanSettingsSerializer,
            scope = CoroutineScope(Dispatchers.IO)
        ) {
            context.dataStoreFile("scan_settings.pb")
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