package su.pank.simplescanner.ui.views.settings

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import su.pank.simplescanner.ui.components.ScanExtensionPick

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SettingsView(settingsViewModel: SettingsViewModel = hiltViewModel<SettingsViewModel>()) {
    val settingUiState by settingsViewModel.settingsUiState.collectAsStateWithLifecycle()

    ScanExtensionPick(settingUiState, settingsViewModel::setExtension)
}

