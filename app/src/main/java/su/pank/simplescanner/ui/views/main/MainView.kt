package su.pank.simplescanner.ui.views.main

import android.app.Activity.RESULT_OK
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import androidx.window.core.layout.WindowSizeClass
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import kotlinx.serialization.Serializable
import su.pank.simplescanner.R
import su.pank.simplescanner.data.models.Scan
import su.pank.simplescanner.data.models.testScan
import su.pank.simplescanner.ui.components.ScansCarousel
import su.pank.simplescanner.ui.components.ScansUiState
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import su.pank.simplescanner.ui.views.settings.SettingsView
import su.pank.simplescanner.utils.DarkLightPreview
import su.pank.simplescanner.utils.LocalSharedTransitionScope
import su.pank.simplescanner.utils.LocalePreview
import su.pank.simplescanner.utils.SharedElementScopeCompositionLocal
import su.pank.simplescanner.utils.currentOrThrow
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Serializable
data object Main : NavKey


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainRoute(
    onListViewOpen: () -> Unit,
    listViewIsOpen: Boolean,
    selectScan: (Scan) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = LocalActivity.current


    val launcher = rememberLauncherForActivityResult(StartIntentSenderForResult()) { result ->

        if (result.resultCode == RESULT_OK) {
            val result = GmsDocumentScanningResult.fromActivityResultIntent(result.data)
            if (result != null) {
                viewModel.saveFile(result, context)

            }
        }


    }


    val scansUiState by viewModel.scansUiState.collectAsStateWithLifecycle()

    MainView(
        scansUiState,
        selectScan,
        {
            if (activity != null)
                viewModel.scan(
                    activity, launcher
                )
        },
        onListViewOpen,
        listViewIsOpen
    )

}


/**
 * @param onListViewOpen --- открытие списка всех сканов
 */
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class,
    ExperimentalSharedTransitionApi::class
)
@Composable
fun MainView(
    scansUiState: ScansUiState,
    selectScan: (Scan) -> Unit,
    scan: () -> Unit,
    onListViewOpen: () -> Unit,
    isListViewOpen: Boolean
) {
    val sharedTransitionScope = LocalSharedTransitionScope.currentOrThrow
    val animatedContentScope = LocalNavAnimatedContentScope.current
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass


    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                stringResource(R.string.recent_title),
            )
        }, actions = {
            val showTabletIcon =
                windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)
            IconButton(onClick = onListViewOpen) {
                if (!showTabletIcon)
                    Icon(painterResource(R.drawable.list), stringResource(R.string.alt_list))
                else {

                    if (isListViewOpen) {
                        Icon(
                            painterResource(R.drawable.right_panel_close),
                            stringResource(R.string.alt_list)
                        )
                    } else {
                        Icon(
                            painterResource(R.drawable.right_panel_open),
                            stringResource(R.string.alt_list)
                        )
                    }

                }
            }
        })
    }) { innerPadding ->
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ScansCarousel(scansUiState, selectScan, modifier = Modifier.fillMaxWidth())
            }

            item {

                with(sharedTransitionScope) {

                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Button(
                            onClick = scan,
                            shapes = ButtonDefaults.shapes(),
                            contentPadding = PaddingValues(48.dp, 32.dp),
                            modifier = Modifier.sharedElement(
                                sharedTransitionScope.rememberSharedContentState("splash-logo-bg"),
                                animatedContentScope
                            )
                        ) {
                            Icon(
                                painterResource(R.drawable.scan), "scanner icon",
                                Modifier
                                    .size(32.dp)
                                    .sharedElement(
                                        sharedTransitionScope.rememberSharedContentState("splash-logo"),
                                        animatedContentScope
                                    )
                            )
                            Spacer(Modifier.width(12.dp))
                            Text(
                                stringResource(R.string.scan),
                                style = MaterialTheme.typography.headlineSmallEmphasized
                            )
                        }
                    }
                }
            }
            item {
                SettingsView()
            }
        }


    }
}


@OptIn(ExperimentalTime::class, ExperimentalSharedTransitionApi::class)
@Preview
@LocalePreview
@DarkLightPreview
@Composable
fun MainViewPreview() {

    LocalContext.current

    SimpleScannerTheme {
        SharedElementScopeCompositionLocal {
            MainView(

                ScansUiState.Success(
                    listOf(
                        testScan, testScan, testScan
                    ), Clock.System.now()
                ),
                {},
                {},
                {},
                isListViewOpen = false
            )
        }
    }
}

