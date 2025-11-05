package su.pank.simplescanner.ui.nav

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import su.pank.simplescanner.ui.views.main.Main
import su.pank.simplescanner.ui.views.splash.Splash
import su.pank.simplescanner.ui.views.splash.SplashView
import su.pank.simplescanner.ui.views.splash_error.SplashError
import su.pank.simplescanner.ui.views.splash_error.SplashErrorView
import su.pank.simplescanner.utils.LocalSharedTransitionScope


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ScannerNav(
    onRestart: () -> Unit,
    backStack: NavBackStack<NavKey>,
    modifier: Modifier = Modifier,
) {
    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this
        ) {


            NavDisplay(
                backStack,
                onBack = { backStack.removeLastOrNull() },
                entryProvider = entryProvider {
                    entry<Splash> {
                        SplashView()
                    }
                    entry<SplashError> {
                        SplashErrorView(it.message) {
                            onRestart()
                        }
                    }
                    entry<Main> {
                        MainNav()
                    }

                },
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                predictivePopTransitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },

                modifier = modifier
            )
        }

    }
}



