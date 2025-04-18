package su.pank.simplescanner

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import su.pank.simplescanner.data.di.dataModule
import su.pank.simplescanner.ui.di.uiModule

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(dataModule, uiModule)
        }
    }
}