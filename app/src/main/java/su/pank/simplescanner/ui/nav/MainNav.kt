package su.pank.simplescanner.ui.nav

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import su.pank.simplescanner.ui.views.main.Main
import su.pank.simplescanner.ui.views.main.MainRoute
import su.pank.simplescanner.ui.views.scan.ScanRoute
import su.pank.simplescanner.ui.views.scan.ScanUI
import su.pank.simplescanner.ui.views.scan.ScanViewModel
import su.pank.simplescanner.ui.views.scans_list.ScanList
import su.pank.simplescanner.ui.views.scans_list.ScanListRoute

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MainNav(
    backStack: NavBackStack<NavKey> = rememberNavBackStack(Main),
    modifier: Modifier = Modifier
) {


    val listDetailSceneStrategy = rememberListDetailSceneStrategy<NavKey>()
    val offsetSpec = MaterialTheme.motionScheme.defaultSpatialSpec<IntOffset>()
    val scanListIsOpen by remember {
        derivedStateOf { backStack.contains(ScanList) }
    }

    NavDisplay(
        backStack,
        modifier = modifier,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            // Add the default decorators for managing scenes and saving state
            rememberSaveableStateHolderNavEntryDecorator(),
            // Then add the view model store decorator
            rememberViewModelStoreNavEntryDecorator()
        ),
        sceneStrategy = listDetailSceneStrategy,
        entryProvider = entryProvider {
            entry<Main>(metadata = ListDetailSceneStrategy.listPane()) {


                MainRoute({
                    if (!scanListIsOpen) {
                        backStack.add(ScanList)
                    } else {
                        backStack.remove(ScanList)
                    }
                }, scanListIsOpen, {
                    backStack.add(ScanUI(it))
                })
            }
            entry<ScanUI>(metadata = ListDetailSceneStrategy.detailPane()) {
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
                EnterTransition.None togetherWith
                        slideOutHorizontally(
                            targetOffsetX = { it },
                            animationSpec = offsetSpec
                        )
            } + NavDisplay.predictivePopTransitionSpec {
                EnterTransition.None togetherWith
                        slideOutHorizontally(
                            targetOffsetX = { it },
                            animationSpec = offsetSpec
                        )
            } + ListDetailSceneStrategy.extraPane()) {

                ScanListRoute({
                    backStack.add(ScanUI(it))
                }, {
                    backStack.removeLastOrNull()
                })
            }
        }
    )

}