package su.pank.simplescanner.ui.nav

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import su.pank.simplescanner.ui.views.main.Main
import su.pank.simplescanner.ui.views.main.MainRoute
import su.pank.simplescanner.ui.views.scan.ScanRoute
import su.pank.simplescanner.ui.views.scan.ScanUI
import su.pank.simplescanner.ui.views.scan.ScanViewModel
import su.pank.simplescanner.ui.views.scans_list.ScanList
import su.pank.simplescanner.ui.views.scans_list.ScanListRoute
import su.pank.simplescanner.ui.views.splash.Splash
import su.pank.simplescanner.ui.views.splash.SplashView
import su.pank.simplescanner.ui.views.splash_error.SplashError
import su.pank.simplescanner.ui.views.splash_error.SplashErrorView
import su.pank.simplescanner.utils.LocalSharedTransitionScope


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ScannerNavHost(
    onRestart: () -> Unit,
    backStack: NavBackStack<NavKey>,
    modifier: Modifier = Modifier,
) {
    val offsetSpec = MaterialTheme.motionScheme.defaultSpatialSpec<IntOffset>()
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
                        MainRoute({
                            backStack.add(ScanList)
                        }, {
                            backStack.add(ScanUI(it))
                        })
                    }
                    entry<ScanUI> {
                        val scanViewModel =
                            hiltViewModel<ScanViewModel, ScanViewModel.Factory>(creationCallback = { factory ->
                                factory.create(it.scan)
                            })

                        ScanRoute(scanViewModel) {
                            backStack.removeLastOrNull()
                        }
                    }
                    entry<ScanList>(metadata = NavDisplay.transitionSpec {
                        slideInHorizontally(
                            initialOffsetX = { it },
                            animationSpec = offsetSpec
                        ) togetherWith ExitTransition.KeepUntilTransitionsFinished
                    } + NavDisplay.popTransitionSpec {
                        // Slide old content down, revealing the new content in place underneath
                        EnterTransition.None togetherWith
                                slideOutHorizontally(
                                    targetOffsetX = { it },
                                    animationSpec = offsetSpec
                                )
                    } + NavDisplay.predictivePopTransitionSpec {
                        // Slide old content down, revealing the new content in place underneath
                        EnterTransition.None togetherWith
                                slideOutHorizontally(
                                    targetOffsetX = { it },
                                    animationSpec = offsetSpec
                                )
                    }

                    ) {

                        ScanListRoute({
                            backStack.add(ScanUI(it))
                        }, {
                            backStack.removeLastOrNull()
                        })
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



