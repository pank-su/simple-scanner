package su.pank.simplescanner.ui.views.settings

import android.content.Intent
import android.content.pm.PackageInfo
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
fun AboutApp(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val activity = LocalActivity.current
    val packageInfo: PackageInfo? = context.packageManager.getPackageInfo(context.packageName, 0)
    var isExpanded by remember { mutableStateOf(true) }
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

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            onClick = {
                activity?.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
            },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    stringResource(R.string.used_libs),
                    style = MaterialTheme.typography.titleLarge
                )
                Icon(painterResource(R.drawable.open_in_new_icon), "Open in new")
            }
        }

    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun AboutAppPreview() {
    SimpleScannerTheme {
        AboutApp()
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