package su.pank.simplescanner.ui.views.settings

import android.content.Intent
import android.content.pm.PackageInfo
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import su.pank.simplescanner.R
import su.pank.simplescanner.data.models.ScanExtension
import su.pank.simplescanner.data.models.ScansSettings
import su.pank.simplescanner.ui.components.ScanExtensionPick
import su.pank.simplescanner.ui.components.SettingsCategory
import su.pank.simplescanner.ui.components.SettingsCategoryShapes
import su.pank.simplescanner.ui.theme.SimpleScannerTheme

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SettingsView(viewModel: SettingsViewModel = hiltViewModel<SettingsViewModel>()) {
    val settingUiState by viewModel.scansSettingsUiState.collectAsStateWithLifecycle()

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        ScanSettings(
            settingUiState,
            viewModel::setExtension,
            onExpand = viewModel::setScansSettingsExpanded
        )
        AboutApp()
    }

}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ScanSettings(
    scansSettingsUiState: ScansSettingsUiState,
    setExtension: (ScanExtension) -> Unit,
    onExpand: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ScanExtensionPick(scansSettingsUiState, setExtension)
        SettingsCategory(
            (scansSettingsUiState as? ScansSettingsUiState.Success)?.settings?.isExpanded == true,
            onExpand, {
                Icon(painterResource(R.drawable.edit_icon), contentDescription = "Edit name")
            }, {
                Text(stringResource(R.string.name_settings))
            }, shapes = SettingsCategoryShapes.top()
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        stringResource(R.string.random_name),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        stringResource(R.string.random_name_desc),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }

                Switch(true, {})
            }

        }

    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AboutApp(modifier: Modifier = Modifier, isExpandedStart: Boolean = false) {
    val context = LocalContext.current
    val activity = LocalActivity.current
    val t = LocalUriHandler.current
    val packageInfo: PackageInfo? = context.packageManager.getPackageInfo(context.packageName, 0)
    var isExpanded by remember { mutableStateOf(isExpandedStart) }
    SettingsCategory(
        isExpanded, { isExpanded = it }, {
            Icon(painterResource(R.drawable.about_icon), contentDescription = "Edit name")
        }, {
            Text(stringResource(R.string.about))
        }, shapes = SettingsCategoryShapes.bottom()
    ) {


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.version), style = MaterialTheme.typography.titleMedium)


            Text(
                packageInfo?.versionName ?: "Unknown",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                stringResource(R.string.used_libs),
                style = MaterialTheme.typography.bodyLarge
            )
            IconButton({
                activity?.startActivity(Intent(context, OssLicensesMenuActivity::class.java))

            }, Modifier.size(24.dp)) {
                Icon(
                    painterResource(R.drawable.open_in_new_icon),
                    "Open in new",
                    Modifier.size(20.dp)
                )


            }

        }
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(onClick = {}, shapes = ButtonDefaults.shapes()) {
                Text("Made with ❤️ by ")
                Text("pank-su ", style = MaterialTheme.typography.titleMediumEmphasized)
                AsyncImage(
                    model = "https://avatars.githubusercontent.com/u/49202787",
                    contentDescription = "Pank-su",
                    modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .size(24.dp)
                )

            }
        }

    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun AboutAppPreview() {
    SimpleScannerTheme {
        AboutApp(isExpandedStart = true)
    }
}

@Preview
@Composable
fun ScanSettingsPreview() {
    val scansSettingsUiState = ScansSettingsUiState.Success(
        settings = ScansSettings(
            true,
            ScanExtension.JPEG
        )
    )
    SimpleScannerTheme {
        ScanSettings(scansSettingsUiState = scansSettingsUiState, {}, {})
    }
}