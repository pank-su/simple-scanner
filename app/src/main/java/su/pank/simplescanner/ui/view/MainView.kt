package su.pank.simplescanner.ui.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.IntentSender
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.google.mlkit.vision.documentscanner.GmsDocumentScanner
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import java.io.File

// TODO: добавить работу с состояниями

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


@Composable
fun MainView() {

    var intentSender: IntentSender? by remember { mutableStateOf(null) }

    val context = LocalContext.current
    val activity = LocalActivity.current

    val scanner = rememberScannerClient()

    val launcher = rememberLauncherForActivityResult(StartIntentSenderForResult()) { result ->
        {
            if (result.resultCode == RESULT_OK) {
                val result =
                    GmsDocumentScanningResult.fromActivityResultIntent(result.data)

                result?.pdf?.uri?.path?.let { path ->
                    val externalUri = FileProvider.getUriForFile(
                        context,
                        activity?.packageName ?: ("" + ".provider"), File(path)
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
    }
    LaunchedEffect(Unit) {
        activity?.let {
            scanner.getStartScanIntent(activity).addOnSuccessListener {
                intentSender = it
            }.addOnFailureListener {
                it.printStackTrace()
            }
        }
    }


    Scaffold {
        Column(Modifier.padding(it)) {
            Button(onClick = {

                if (intentSender != null) launcher.launch(
                    IntentSenderRequest.Builder(intentSender!!).build()
                )
            }, enabled = intentSender != null) {
                Text("Сканировать")
            }
        }
    }
}