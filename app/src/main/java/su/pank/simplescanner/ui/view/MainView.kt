package su.pank.simplescanner.ui.view

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.google.mlkit.vision.documentscanner.GmsDocumentScanner
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import su.pank.simplescanner.R
import su.pank.simplescanner.ui.theme.headlineSmallFontFamily
import su.pank.simplescanner.ui.theme.icons.Cat
import su.pank.simplescanner.ui.theme.icons.Scanner
import java.io.File

@Composable
fun rememberScannerClient(): GmsDocumentScanner {
    return remember {
        val options = GmsDocumentScannerOptions.Builder()
            .setGalleryImportAllowed(true)
            .setResultFormats(RESULT_FORMAT_PDF)
            .setScannerMode(SCANNER_MODE_FULL)
            .build()
        return@remember GmsDocumentScanning.getClient(options)
    }

}


@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {

    var intentSender: IntentSender? by remember { mutableStateOf(null) }


    val context = LocalContext.current
    val activity = LocalActivity.current

    val scanner = rememberScannerClient()

    val launcher = rememberLauncherForActivityResult(StartIntentSenderForResult()) { result ->

        if (result.resultCode == RESULT_OK) {
            val result =
                GmsDocumentScanningResult.fromActivityResultIntent(result.data)


            result?.pdf?.uri?.path?.let { path ->
                val externalUri = FileProvider.getUriForFile(
                    context,
                    activity?.packageName + ".provider", File(path)
                )
                val shareIntent =
                    Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_STREAM, externalUri)
                        type = "application/pdf"
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                activity?.startActivity(Intent.createChooser(shareIntent, "share pdf"))
            }
        }

    }
    LaunchedEffect(Unit) {
        activity?.let {
            scanner.getStartScanIntent(activity).addOnSuccessListener {
                intentSender = it
                it
            }.addOnFailureListener {
                // TODO: добавить  state если не загрузилось
            }
        }
    }


    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                stringResource(R.string.recent_title),
                style = MaterialTheme.typography.headlineSmall
            )
        }, actions = {
            IconButton(onClick = {}) {
                //Icon()
            }
        })
    }) {
        Column(Modifier
            .padding(it)
            .padding(12.dp)
            .fillMaxWidth()) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    onClick = {
                        if (intentSender != null) launcher.launch(
                            IntentSenderRequest.Builder(intentSender!!).build()
                        )
                    },
                    modifier = Modifier
                        .shadow(3.dp, RoundedCornerShape(16.dp))
                        .height(82.dp),
                    contentPadding = PaddingValues(21.dp)
                ) {
                    Icon(painterResource(R.drawable.scan), "scanner icon", Modifier.size(40.dp))
                    Spacer(Modifier.width(12.dp))
                    Text(
                        stringResource(R.string.scan),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }

        }
    }
}