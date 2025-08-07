package su.pank.simplescanner.ui.theme

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import su.pank.simplescanner.R


fun counterWidth(counterWidth: Int): FontVariation.Setting {
    require(counterWidth in 323..603) { "'Counter width' must be in 323..603" }
    return FontVariation.Setting("XTRA", counterWidth.toFloat())
}

@OptIn(ExperimentalTextApi::class)
val headlineSmallFontFamily = FontFamily(
    Font(
        R.font.roboto_flex, variationSettings = FontVariation.Settings(
            FontVariation.slant(-7f),
            FontVariation.width(124f),
            FontVariation.weight(600),
            counterWidth(468),
            FontVariation.Setting("YTDE", -203f),
            FontVariation.Setting("YTFI", 738f),
            FontVariation.Setting("YTLC", 514f),
            FontVariation.Setting("YTUC", 712f)
            //FontVariation.Setting("Xtra", 536f)
        )
    )
)

// Set of Material typography styles to start with
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineSmallEmphasized = TextStyle(
        fontFamily = headlineSmallFontFamily,
        fontSize = 24.sp,
        lineHeight = 32.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)