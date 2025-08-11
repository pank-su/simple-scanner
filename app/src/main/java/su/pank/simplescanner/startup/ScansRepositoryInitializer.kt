package su.pank.simplescanner.startup

import android.content.Context
import androidx.startup.Initializer
import su.pank.simplescanner.data.scans.ScansRepository
import su.pank.simplescanner.domain.SaveScanUseCase
import javax.inject.Inject

class SaveScanUseCaseInitializer: Initializer<SaveScanUseCase> {

    @Inject
    lateinit var saveScanUseCase: SaveScanUseCase

    override fun create(context: Context): SaveScanUseCase {
        InitializerEntryPoint.resolve(context).inject(this)
        return saveScanUseCase
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?>  = listOf(DependencyGraphInitializer::class.java)


}