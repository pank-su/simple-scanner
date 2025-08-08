package su.pank.simplescanner.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import su.pank.simplescanner.R
import su.pank.simplescanner.ui.nav.ScannerNavHost
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import su.pank.simplescanner.ui.views.main.Main
import su.pank.simplescanner.ui.views.splash.Splash
import su.pank.simplescanner.ui.views.splash_error.SplashError
import kotlin.time.Duration.Companion.seconds


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)


        splashScreen.setKeepOnScreenCondition { viewModel.state.value == MainActivityState.Loading }

        splashScreen.setOnExitAnimationListener { splashScreenProvider ->
            val splashScreenView = splashScreenProvider.view

            val animator = ObjectAnimator.ofFloat(
                splashScreenView,
                View.ALPHA,
                1f,
                0f
            ).apply {
                interpolator = AnticipateInterpolator()
                duration = 100L
                doOnEnd { splashScreenProvider.remove() }
            }

            animator.start()
        }

        // FIX: System bar colors

        viewModel.checkGoogleServicesAndLoadData(this)

        setContent {
            val navController = rememberNavController()

            val state by viewModel.state.collectAsState()

            val updateString = stringResource(R.string.error_no_services)

            SimpleScannerTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface)
                )
                // TODO: NEED SCREENS TO SHOW STATE
                ScannerNavHost(onRestart = ::restartApp, navController = navController)
                //StatusBarProtection()


                LaunchedEffect(state) {
                    if (state is MainActivityState.Loading) {
                        return@LaunchedEffect
                    }
                    delay(0.3.seconds)
                    navController.navigate(
                        when (state) {
                            is MainActivityState.Error -> SplashError((state as MainActivityState.Error).message)
                            MainActivityState.Loading -> Splash
                            MainActivityState.NeedInstallGoogleServices -> SplashError(updateString) // TODO: maybe use update google services dialog
                            is MainActivityState.Success -> Main
                        }
                    )
                }


            }
        }
    }

    fun restartApp() {
        val intent: Intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
        this.finishAffinity()
    }


}


@Composable
private fun StatusBarProtection(
    color: Color = MaterialTheme.colorScheme.surfaceContainer,
    heightProvider: () -> Float = calculateGradientHeight(),
) {

    Canvas(Modifier.fillMaxSize()) {
        val calculatedHeight = heightProvider()
        val gradient = Brush.verticalGradient(
            colors = listOf(
                color.copy(alpha = 1f),
                color.copy(alpha = .8f),
                Color.Transparent
            ),
            startY = 0f,
            endY = calculatedHeight
        )
        drawRect(
            brush = gradient,
            size = Size(size.width, calculatedHeight),
        )
    }
}

@Composable
fun calculateGradientHeight(): () -> Float {
    val statusBars = WindowInsets.statusBars
    val density = LocalDensity.current
    return { statusBars.getTop(density).times(1.2f) }
}