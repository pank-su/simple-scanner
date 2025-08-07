package su.pank.simplescanner.ui.views.main

import android.app.Activity.RESULT_OK
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.mlkit.vision.documentscanner.GmsDocumentScanner
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import kotlinx.serialization.Serializable
import su.pank.simplescanner.R
import su.pank.simplescanner.data.models.TestItem
import su.pank.simplescanner.proto.scansSettings
import su.pank.simplescanner.ui.components.ScansCarousel
import su.pank.simplescanner.ui.components.ScansUiState
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import su.pank.simplescanner.utils.DarkLightPreview
import su.pank.simplescanner.utils.LocalePreview
import java.io.File
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Serializable
object Main

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainRoute(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onListViewOpen: () -> Unit,
    onSuccessScan: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = LocalActivity.current


    // TODO: replace to activity
    val launcher = rememberLauncherForActivityResult(StartIntentSenderForResult()) { result ->

        if (result.resultCode == RESULT_OK) {
            val result = GmsDocumentScanningResult.fromActivityResultIntent(result.data)

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
        sharedTransitionScope,
        animatedContentScope,
        scansUiState,
        settingUiState,
        {},
        onListViewOpen
    )

}

@Composable
fun rememberScannerClient(): GmsDocumentScanner {
    return remember {
        val options = GmsDocumentScannerOptions.Builder().setGalleryImportAllowed(true)
            .setResultFormats(RESULT_FORMAT_PDF).setScannerMode(SCANNER_MODE_FULL).build()

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
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    scansUiState: ScansUiState,
    settingUiState: ScansSettingsUiState,
    scan: () -> Unit,
    onListViewOpen: () -> Unit
) {


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
    }) {
        Column(
            Modifier
                .padding(it)
                .padding(12.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            ScansCarousel(scansUiState, {})

            with(sharedTransitionScope) {

                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(
                        onClick = scan,
                        shapes = ButtonDefaults.shapes(),
                        contentPadding = PaddingValues(48.dp, 32.dp)
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
        SharedTransitionLayout {
            AnimatedContent(true) { _ ->
                MainView(
                    this@SharedTransitionLayout, this@AnimatedContent,
                    ScansUiState.Success(
                        listOf(
                            TestItem(context),
                            TestItem(context),
                            TestItem(context),
                            TestItem(context),
                            TestItem(context)
                        ), Clock.System.now()
                    ),
                    ScansSettingsUiState.Success(scansSettings { }),
                    {}) { }
            }
        }
    }
}