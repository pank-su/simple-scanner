package su.pank.simplescanner.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme", group = "Themes")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme", group = "Themes")
annotation class DarkLightPreview
