package su.pank.simplescanner

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.request.crossfade
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp
import su.pank.simplescanner.coil.pdf.PdfDecoder
import javax.inject.Inject

@HiltAndroidApp
class MainApplication: Application(), SingletonImageLoader.Factory  {



    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(context)
            .crossfade(true).components {
                add(PdfDecoder.Factory())
            }
            .build()
    }


}