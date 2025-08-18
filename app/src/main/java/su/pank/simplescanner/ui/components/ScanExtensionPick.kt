package su.pank.simplescanner.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import su.pank.simplescanner.R
import su.pank.simplescanner.data.models.ScanExtension
import su.pank.simplescanner.ui.views.settings.SettingsUiState

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ScanExtensionPick(
    settingUiState: SettingsUiState,
    setExtension: (ScanExtension) -> Unit,
    modifier: Modifier = Modifier
) {
    val extensions = ScanExtension.entries
    Row(
        Modifier.padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
    ) {
        extensions.forEachIndexed { index, extension ->
            ToggleButton(
                checked = (settingUiState as? SettingsUiState.Success)?.settings?.extension == extension,
                onCheckedChange = { setExtension(extension) },
                enabled = settingUiState != SettingsUiState.Loading,
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
                            ScanExtension.PDF -> R.drawable.pdf_icon
                            ScanExtension.JPEG -> R.drawable.jpeg_icon
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