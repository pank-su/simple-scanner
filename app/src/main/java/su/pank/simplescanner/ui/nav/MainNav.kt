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
import su.pank.simplescanner.ui.splash_error.SplashErrorView
import su.pank.simplescanner.ui.splash.Splash
import su.pank.simplescanner.ui.splash.SplashView

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ScannerNavHost(
    onRestart: () -> Unit,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    SharedTransitionLayout {
        NavHost(navController, startDestination = Splash, modifier) {
            composable<Splash> {
                SplashView(this@SharedTransitionLayout, this@composable)
            }
            composable<su.pank.simplescanner.ui.splash_error.Error> {
                val msg = it.toRoute<su.pank.simplescanner.ui.splash_error.Error>().message
                SplashErrorView(msg) {
                    onRestart()
                }
            }
        }
    }


}