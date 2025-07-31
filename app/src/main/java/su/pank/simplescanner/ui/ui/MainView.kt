package su.pank.simplescanner.ui.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.google.mlkit.vision.documentscanner.GmsDocumentScanner
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import su.pank.simplescanner.R
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import java.io.File

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
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MainView(scan: () -> Unit, onListViewOpen: () -> Unit) {

    var intentSender: IntentSender? by remember { mutableStateOf(null) }


    val context = LocalContext.current
    val activity = LocalActivity.current

    val scanner = rememberScannerClient()

    // TODO: убрать эту логику из ui
    val launcher = rememberLauncherForActivityResult(StartIntentSenderForResult()) { result ->

        if (result.resultCode == RESULT_OK) {
            val result = GmsDocumentScanningResult.fromActivityResultIntent(result.data)


            result?.pdf?.uri?.path?.let { path ->
                val externalUri = FileProvider.getUriForFile(
                    context, activity?.packageName + ".provider", File(path)
                )
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_STREAM, externalUri)
                    type = "application/pdf"
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                activity?.startActivity(Intent.createChooser(shareIntent, "share pdf"))
            }
        }

    }

    // TODO: убрать эту логику в SplashScreen
    LaunchedEffect(Unit) {
        activity?.let {
            scanner.getStartScanIntent(activity).addOnSuccessListener {
                intentSender = it
                it
            }.addOnFailureListener {
                Log.d("MainScreen", it.stackTraceToString())
                // TODO: добавить  state если не загрузилось
            }
        }
    }


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
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    onClick = {
                        if (intentSender != null) launcher.launch(
                            IntentSenderRequest.Builder(intentSender!!).build()
                        )
                    },
                    shapes = ButtonDefaults.shapes(),
                    contentPadding = PaddingValues(48.dp, 32.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.scan), "scanner icon",
                        Modifier.size(32.dp)
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

@Preview
@Composable
fun MainViewPreview() {
    SimpleScannerTheme {
        MainView({}) { }
    }
}