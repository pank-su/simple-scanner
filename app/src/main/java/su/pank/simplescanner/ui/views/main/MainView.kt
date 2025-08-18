package su.pank.simplescanner.ui.views.main

import android.app.Activity.RESULT_OK
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.mlkit.vision.documentscanner.GmsDocumentScanner
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_JPEG
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import kotlinx.serialization.Serializable
import su.pank.simplescanner.R
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.data.models.TestItem
import su.pank.simplescanner.proto.Extension
import su.pank.simplescanner.proto.scansSettings
import su.pank.simplescanner.ui.components.ScansCarousel
import su.pank.simplescanner.ui.components.ScansUiState
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import su.pank.simplescanner.utils.DarkLightPreview
import su.pank.simplescanner.utils.LocalNavAnimatedVisibilityScope
import su.pank.simplescanner.utils.LocalSharedTransitionScope
import su.pank.simplescanner.utils.LocalePreview
import su.pank.simplescanner.utils.SharedElementScopeCompositionLocal
import su.pank.simplescanner.utils.currentOrThrow
import java.io.File
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Serializable
object Main


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainRoute(
    onListViewOpen: () -> Unit,
    selectScan: (ScannedItem) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = LocalActivity.current


    val launcher = rememberLauncherForActivityResult(StartIntentSenderForResult()) { result ->

        if (result.resultCode == RESULT_OK) {
            val result = GmsDocumentScanningResult.fromActivityResultIntent(result.data)
            if (result != null) {
                val scanItem: ScannedItem = viewModel.saveFile(result, context)
                selectScan(scanItem)
                return@rememberLauncherForActivityResult

            }
            // TODO: проверить нет ли ошибок при ином исходе
            result?.pdf?.uri?.path?.let { path ->
                // Переход в следующий экран с файлом

                val externalUri = FileProvider.getUriForFile(
                    context, activity?.packageName + ".provider", File(path)
                )

            }
        }

    }

    val scansUiState by viewModel.scansUiState.collectAsStateWithLifecycle()
    val settingUiState by viewModel.settingsUiState.collectAsStateWithLifecycle()

    MainView(
        scansUiState,
        settingUiState,
        selectScan,
        {
            if (activity != null)
                viewModel.scan(
                    activity, GmsDocumentScannerOptions.Builder().setGalleryImportAllowed(true)
                        .setResultFormats(RESULT_FORMAT_JPEG).build(), launcher
                )
        },
        onListViewOpen,
        viewModel::setExtension
    )

}

@Composable
fun rememberScannerClient(): GmsDocumentScanner {
    return remember {
        val options = GmsDocumentScannerOptions.Builder().setGalleryImportAllowed(true)
            .setResultFormats(RESULT_FORMAT_JPEG).setScannerMode(SCANNER_MODE_FULL).build()

        return@remember GmsDocumentScanning.getClient(options)
    }

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
    settingUiState: SettingsUiState,
    selectScan: (ScannedItem) -> Unit,
    scan: () -> Unit,
    onListViewOpen: () -> Unit,
    setExtension: (Extension) -> Unit
) {
    val sharedTransitionScope = LocalSharedTransitionScope.currentOrThrow
    val animatedContentScope = LocalNavAnimatedVisibilityScope.currentOrThrow


    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                stringResource(R.string.recent_title),
            )
        }, actions = {
            IconButton(onClick = onListViewOpen) {
                Icon(painterResource(R.drawable.list), stringResource(R.string.alt_list))
            }
        })
    }) { innerPadding ->
        LazyColumn(
            Modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
                val isLoading = settingUiState is SettingsUiState.Loading
                val extensions =
                    Extension.entries.filter { it != Extension.UNRECOGNIZED }.toTypedArray()

                Row(
                    Modifier.padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
                ) {
                    extensions.forEachIndexed { index, extension ->
                        ToggleButton(
                            checked = (settingUiState as? SettingsUiState.Success)?.settings?.extension == extension,
                            onCheckedChange = { setExtension(extension) },
                            enabled = !isLoading,
                            modifier = Modifier
                                .weight(1f)
                                .semantics { role = Role.RadioButton },
                            shapes = when (index) {
                                0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                                extensions.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                                else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                            }

                        ) {
                            Icon(
                                painterResource(
                                    when (extension) {
                                        Extension.PDF -> R.drawable.pdf_icon
                                        Extension.JPG -> R.drawable.jpeg_icon
                                        Extension.UNRECOGNIZED -> R.drawable.scan
                                    }
                                ),
                                contentDescription = "Localized description",
                            )
                            Spacer(Modifier.size(ToggleButtonDefaults.IconSpacing))
                            Text(extension.name)
                        }

                    }

                }
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

    val context = LocalContext.current

    SimpleScannerTheme {
        SharedElementScopeCompositionLocal {
            MainView(

                ScansUiState.Success(
                    listOf(
                        TestItem, TestItem, TestItem
                    ), Clock.System.now()
                ),
                SettingsUiState.Success(scansSettings { }), {},
                {}, {}) { }
        }
    }
}

