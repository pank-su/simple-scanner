package su.pank.simplescanner.ui.nav

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import su.pank.simplescanner.ui.views.main.Main
import su.pank.simplescanner.ui.views.main.MainRoute
import su.pank.simplescanner.ui.views.scan.Scan
import su.pank.simplescanner.ui.views.scan.ScanRoute
import su.pank.simplescanner.ui.views.splash.Splash
import su.pank.simplescanner.ui.views.splash.SplashView
import su.pank.simplescanner.ui.views.splash_error.SplashError
import su.pank.simplescanner.ui.views.splash_error.SplashErrorView
import su.pank.simplescanner.utils.AnimatedScopeProvider
import su.pank.simplescanner.utils.LocalSharedTransitionScope


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ScannerNavHost(
    onRestart: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {


    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this
        ) {
            NavHost(navController, startDestination = Splash, modifier) {

                composable<Splash> {
                    AnimatedScopeProvider {
                        SplashView()
                    }
                }
                composable<SplashError> {
                    val msg = it.toRoute<SplashError>().message
                    AnimatedScopeProvider {

                        SplashErrorView(msg) {
                            onRestart()
                        }
                    }
                }
                composable<Main> {
                    AnimatedScopeProvider {

                        MainRoute({

                        }, {
                            navController.navigate(Scan(it) )
                        })
                    }
                }
                composable<Scan>(typeMap = Scan.typeMap) { entry ->
                    AnimatedScopeProvider {
                        ScanRoute()
                    }
                }
            }


        }

    }
}



