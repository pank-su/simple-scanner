package su.pank.simplescanner.ui.nav

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import su.pank.simplescanner.ui.splash.Splash
import su.pank.simplescanner.ui.splash.SplashView
import su.pank.simplescanner.ui.views.main.Main
import su.pank.simplescanner.ui.views.main.MainRoute
import su.pank.simplescanner.ui.views.splash_error.SplashError
import su.pank.simplescanner.ui.views.splash_error.SplashErrorView

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ScannerNavHost(
    onRestart: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    SharedTransitionLayout {
        NavHost(navController, startDestination = Splash, modifier) {
            composable<Splash> {
                SplashView(this@SharedTransitionLayout, this@composable)
            }
            composable<SplashError> {
                val msg = it.toRoute<SplashError>().message
                SplashErrorView(msg) {
                    onRestart()
                }
            }
            composable<Main> {
                MainRoute()
            }
        }
    }


}