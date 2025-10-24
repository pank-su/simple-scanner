package su.pank.simplescanner.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import su.pank.simplescanner.database.ScannerDatabase

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideScanDao(database: ScannerDatabase) = database.scanDao()
}