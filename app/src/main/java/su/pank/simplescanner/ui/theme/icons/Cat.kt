package su.pank.simplescanner.ui.theme.icons

import su.pank.simplescanner.ui.theme.SimpleScannerTheme


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp




val Cat: ImageVector
    @Composable
    get() {
        val theme = MaterialTheme.colorScheme
        val current = _cat
        if (current != null && theme.primary.hashCode() == current.first) return current.second

        return ImageVector.Builder(
            defaultWidth = 636.0.dp,
            defaultHeight = 831.0.dp,
            viewportWidth = 636.0f,
            viewportHeight = 831.0f,
        ).apply {
            group(
                // M 0 0 H 635.08 V 830.09 H 0z
                clipPathData = PathData {
                    // M 0 0
                    moveTo(x = 0.0f, y = 0.0f)
                    // H 635.08
                    horizontalLineTo(x = 635.08f)
                    // V 830.09
                    verticalLineTo(y = 830.09f)
                    // H 0z
                    horizontalLineTo(x = 0.0f)
                    close()
                },
            ) {

                // M283.5 775.09 A282.5 27.5 0 1 0 283.5 830.09 282.5 27.5 0 1 0 283.5 775.09z
                path(
                    fill = SolidColor(theme.surfaceVariant),
                ) {
                    // M 283.5 775.09
                    moveTo(x = 283.5f, y = 775.09f)
                    // A 282.5 27.5 0 1 0 283.5 830.09
                    arcTo(
                        horizontalEllipseRadius = 282.5f,
                        verticalEllipseRadius = 27.5f,
                        theta = 0.0f,
                        isMoreThanHalf = true,
                        isPositiveArc = false,
                        x1 = 283.5f,
                        y1 = 830.09f,
                    )
                    // A 282.5 27.5 0 1 0 283.5 775.09z
                    arcTo(
                        horizontalEllipseRadius = 282.5f,
                        verticalEllipseRadius = 27.5f,
                        theta = 0.0f,
                        isMoreThanHalf = true,
                        isPositiveArc = false,
                        x1 = 283.5f,
                        y1 = 775.09f,
                    )
                    close()
                }
                // M399.82 126.1 C418.09 142.33 443 197.7 443 197.7 c3.85 -.26 15.93 -83.2 11.5 -139.6 -2.55 -32.4 -9.17 -53.38 -24 -56.5 -40.63 -8.52 -126.63 75 -127 78 0 0 79.32 31.43 96.32 46.52
                path(
                    fill = SolidColor(MaterialTheme.colorScheme.primary),
                ) {
                    // M 399.82 126.1
                    moveTo(x = 399.82f, y = 126.1f)
                    // C 418.09 142.33 443 197.7 443 197.7
                    curveTo(
                        x1 = 418.09f,
                        y1 = 142.33f,
                        x2 = 443.0f,
                        y2 = 197.7f,
                        x3 = 443.0f,
                        y3 = 197.7f,
                    )
                    // c 3.85 -0.26 15.93 -83.2 11.5 -139.6
                    curveToRelative(
                        dx1 = 3.85f,
                        dy1 = -0.26f,
                        dx2 = 15.93f,
                        dy2 = -83.2f,
                        dx3 = 11.5f,
                        dy3 = -139.6f,
                    )
                    // c -2.55 -32.4 -9.17 -53.38 -24 -56.5
                    curveToRelative(
                        dx1 = -2.55f,
                        dy1 = -32.4f,
                        dx2 = -9.17f,
                        dy2 = -53.38f,
                        dx3 = -24.0f,
                        dy3 = -56.5f,
                    )
                    // c -40.63 -8.52 -126.63 75 -127 78
                    curveToRelative(
                        dx1 = -40.63f,
                        dy1 = -8.52f,
                        dx2 = -126.63f,
                        dy2 = 75.0f,
                        dx3 = -127.0f,
                        dy3 = 78.0f,
                    )
                    // c 0 0 79.32 31.43 96.32 46.52
                    curveToRelative(
                        dx1 = 0.0f,
                        dy1 = 0.0f,
                        dx2 = 79.32f,
                        dy2 = 31.43f,
                        dx3 = 96.32f,
                        dy3 = 46.52f,
                    )
                }
                // M394.5 128.69 c18.27 16.21 38.15 48.15 38.15 48.15 2.72 2.33 26.25 -155.61 -9 -159 -34.53 -3.31 -79.1 78.7 -80.14 80.35 h-.01 q-.02 .06 0 0 c.59 .26 33.94 15.35 51 30.5
                path(
                    fill = SolidColor(theme.primaryContainer),
                ) {
                    // M 394.5 128.69
                    moveTo(x = 394.5f, y = 128.69f)
                    // c 18.27 16.21 38.15 48.15 38.15 48.15
                    curveToRelative(
                        dx1 = 18.27f,
                        dy1 = 16.21f,
                        dx2 = 38.15f,
                        dy2 = 48.15f,
                        dx3 = 38.15f,
                        dy3 = 48.15f,
                    )
                    // c 2.72 2.33 26.25 -155.61 -9 -159
                    curveToRelative(
                        dx1 = 2.72f,
                        dy1 = 2.33f,
                        dx2 = 26.25f,
                        dy2 = -155.61f,
                        dx3 = -9.0f,
                        dy3 = -159.0f,
                    )
                    // c -34.53 -3.31 -79.1 78.7 -80.14 80.35
                    curveToRelative(
                        dx1 = -34.53f,
                        dy1 = -3.31f,
                        dx2 = -79.1f,
                        dy2 = 78.7f,
                        dx3 = -80.14f,
                        dy3 = 80.35f,
                    )
                    // h -0.01
                    horizontalLineToRelative(dx = -0.01f)
                    // q -0.02 0.06 0 0
                    quadToRelative(
                        dx1 = -0.02f,
                        dy1 = 0.06f,
                        dx2 = 0.0f,
                        dy2 = 0.0f,
                    )
                    // c 0.59 0.26 33.94 15.35 51 30.5
                    curveToRelative(
                        dx1 = 0.59f,
                        dy1 = 0.26f,
                        dx2 = 33.94f,
                        dy2 = 15.35f,
                        dx3 = 51.0f,
                        dy3 = 30.5f,
                    )
                }
                // M140.18 804.1 130 551.1 h296.5 l11.5 253z
                path(
                    fill = Brush.linearGradient(
                        0.0f to theme.surface,
                        1.0f to theme.secondaryContainer,
                        start = Offset(x = 184.0f, y = 587.094f),
                        end = Offset(x = 472.422f, y = 757.182f),
                    ),
                ) {
                    // M 140.18 804.1
                    moveTo(x = 140.18f, y = 804.1f)
                    // L 130 551.1
                    lineTo(x = 130.0f, y = 551.1f)
                    // h 296.5
                    horizontalLineToRelative(dx = 296.5f)
                    // l 11.5 253z
                    lineToRelative(dx = 11.5f, dy = 253.0f)
                    close()
                }
                // M500.5 783.6 c-9.19 2.99 -24 6 -24 6 l40.5 -101 s10.52 -2.95 16 -7 c5.57 -4.12 7.52 -7.84 11.5 -13.5 4.97 -7.09 7.2 -11.51 10.5 -19.5 3.43 -8.28 4.72 -13.23 6.5 -22 1.6 -7.91 2.13 -12.45 2.5 -20.5 .43 -9.38 .79 -14.8 -1 -24 -1.45 -7.46 -3.33 -11.4 -6 -18.5 -2.62 -6.97 -4.54 -10.69 -7.5 -17.5 -3.66 -8.43 -8.28 -12.4 -9.5 -21.5 -.94 -7 -.97 -11.4 1.5 -18 3.77 -10.1 9.34 -15.95 19.5 -19.5 11 -3.86 19.17 -.9 29.5 4.5 9.91 5.17 13.48 11.4 20 20.5 6.16 8.58 8.38 14.26 12.5 24 4.1 9.66 6.34 15.22 8.5 25.5 1.94 9.22 1.82 14.6 2.5 24 .7 9.54 1.31 14.92 1 24.5 -.37 11.4 -1.49 17.77 -3.5 29 -1.7 9.46 -2.5 14.86 -5.5 24 -3.54 10.79 -6.7 16.45 -12 26.5 -4.78 9.06 -7.4 14.26 -13.5 22.5 -5.6 7.55 -9.42 11.28 -16 18 -8.8 8.98 -13.96 13.91 -24 21.5 -7.65 5.77 -12.23 8.64 -20.5 13.5 -14.7 8.61 -23.3 13.22 -39.5 18.5
                path(
                    fill = SolidColor(theme.primary),
                ) {
                    // M 500.5 783.6
                    moveTo(x = 500.5f, y = 783.6f)
                    // c -9.19 2.99 -24 6 -24 6
                    curveToRelative(
                        dx1 = -9.19f,
                        dy1 = 2.99f,
                        dx2 = -24.0f,
                        dy2 = 6.0f,
                        dx3 = -24.0f,
                        dy3 = 6.0f,
                    )
                    // l 40.5 -101
                    lineToRelative(dx = 40.5f, dy = -101.0f)
                    // s 10.52 -2.95 16 -7
                    reflectiveCurveToRelative(
                        dx1 = 10.52f,
                        dy1 = -2.95f,
                        dx2 = 16.0f,
                        dy2 = -7.0f,
                    )
                    // c 5.57 -4.12 7.52 -7.84 11.5 -13.5
                    curveToRelative(
                        dx1 = 5.57f,
                        dy1 = -4.12f,
                        dx2 = 7.52f,
                        dy2 = -7.84f,
                        dx3 = 11.5f,
                        dy3 = -13.5f,
                    )
                    // c 4.97 -7.09 7.2 -11.51 10.5 -19.5
                    curveToRelative(
                        dx1 = 4.97f,
                        dy1 = -7.09f,
                        dx2 = 7.2f,
                        dy2 = -11.51f,
                        dx3 = 10.5f,
                        dy3 = -19.5f,
                    )
                    // c 3.43 -8.28 4.72 -13.23 6.5 -22
                    curveToRelative(
                        dx1 = 3.43f,
                        dy1 = -8.28f,
                        dx2 = 4.72f,
                        dy2 = -13.23f,
                        dx3 = 6.5f,
                        dy3 = -22.0f,
                    )
                    // c 1.6 -7.91 2.13 -12.45 2.5 -20.5
                    curveToRelative(
                        dx1 = 1.6f,
                        dy1 = -7.91f,
                        dx2 = 2.13f,
                        dy2 = -12.45f,
                        dx3 = 2.5f,
                        dy3 = -20.5f,
                    )
                    // c 0.43 -9.38 0.79 -14.8 -1 -24
                    curveToRelative(
                        dx1 = 0.43f,
                        dy1 = -9.38f,
                        dx2 = 0.79f,
                        dy2 = -14.8f,
                        dx3 = -1.0f,
                        dy3 = -24.0f,
                    )
                    // c -1.45 -7.46 -3.33 -11.4 -6 -18.5
                    curveToRelative(
                        dx1 = -1.45f,
                        dy1 = -7.46f,
                        dx2 = -3.33f,
                        dy2 = -11.4f,
                        dx3 = -6.0f,
                        dy3 = -18.5f,
                    )
                    // c -2.62 -6.97 -4.54 -10.69 -7.5 -17.5
                    curveToRelative(
                        dx1 = -2.62f,
                        dy1 = -6.97f,
                        dx2 = -4.54f,
                        dy2 = -10.69f,
                        dx3 = -7.5f,
                        dy3 = -17.5f,
                    )
                    // c -3.66 -8.43 -8.28 -12.4 -9.5 -21.5
                    curveToRelative(
                        dx1 = -3.66f,
                        dy1 = -8.43f,
                        dx2 = -8.28f,
                        dy2 = -12.4f,
                        dx3 = -9.5f,
                        dy3 = -21.5f,
                    )
                    // c -0.94 -7 -0.97 -11.4 1.5 -18
                    curveToRelative(
                        dx1 = -0.94f,
                        dy1 = -7.0f,
                        dx2 = -0.97f,
                        dy2 = -11.4f,
                        dx3 = 1.5f,
                        dy3 = -18.0f,
                    )
                    // c 3.77 -10.1 9.34 -15.95 19.5 -19.5
                    curveToRelative(
                        dx1 = 3.77f,
                        dy1 = -10.1f,
                        dx2 = 9.34f,
                        dy2 = -15.95f,
                        dx3 = 19.5f,
                        dy3 = -19.5f,
                    )
                    // c 11 -3.86 19.17 -0.9 29.5 4.5
                    curveToRelative(
                        dx1 = 11.0f,
                        dy1 = -3.86f,
                        dx2 = 19.17f,
                        dy2 = -0.9f,
                        dx3 = 29.5f,
                        dy3 = 4.5f,
                    )
                    // c 9.91 5.17 13.48 11.4 20 20.5
                    curveToRelative(
                        dx1 = 9.91f,
                        dy1 = 5.17f,
                        dx2 = 13.48f,
                        dy2 = 11.4f,
                        dx3 = 20.0f,
                        dy3 = 20.5f,
                    )
                    // c 6.16 8.58 8.38 14.26 12.5 24
                    curveToRelative(
                        dx1 = 6.16f,
                        dy1 = 8.58f,
                        dx2 = 8.38f,
                        dy2 = 14.26f,
                        dx3 = 12.5f,
                        dy3 = 24.0f,
                    )
                    // c 4.1 9.66 6.34 15.22 8.5 25.5
                    curveToRelative(
                        dx1 = 4.1f,
                        dy1 = 9.66f,
                        dx2 = 6.34f,
                        dy2 = 15.22f,
                        dx3 = 8.5f,
                        dy3 = 25.5f,
                    )
                    // c 1.94 9.22 1.82 14.6 2.5 24
                    curveToRelative(
                        dx1 = 1.94f,
                        dy1 = 9.22f,
                        dx2 = 1.82f,
                        dy2 = 14.6f,
                        dx3 = 2.5f,
                        dy3 = 24.0f,
                    )
                    // c 0.7 9.54 1.31 14.92 1 24.5
                    curveToRelative(
                        dx1 = 0.7f,
                        dy1 = 9.54f,
                        dx2 = 1.31f,
                        dy2 = 14.92f,
                        dx3 = 1.0f,
                        dy3 = 24.5f,
                    )
                    // c -0.37 11.4 -1.49 17.77 -3.5 29
                    curveToRelative(
                        dx1 = -0.37f,
                        dy1 = 11.4f,
                        dx2 = -1.49f,
                        dy2 = 17.77f,
                        dx3 = -3.5f,
                        dy3 = 29.0f,
                    )
                    // c -1.7 9.46 -2.5 14.86 -5.5 24
                    curveToRelative(
                        dx1 = -1.7f,
                        dy1 = 9.46f,
                        dx2 = -2.5f,
                        dy2 = 14.86f,
                        dx3 = -5.5f,
                        dy3 = 24.0f,
                    )
                    // c -3.54 10.79 -6.7 16.45 -12 26.5
                    curveToRelative(
                        dx1 = -3.54f,
                        dy1 = 10.79f,
                        dx2 = -6.7f,
                        dy2 = 16.45f,
                        dx3 = -12.0f,
                        dy3 = 26.5f,
                    )
                    // c -4.78 9.06 -7.4 14.26 -13.5 22.5
                    curveToRelative(
                        dx1 = -4.78f,
                        dy1 = 9.06f,
                        dx2 = -7.4f,
                        dy2 = 14.26f,
                        dx3 = -13.5f,
                        dy3 = 22.5f,
                    )
                    // c -5.6 7.55 -9.42 11.28 -16 18
                    curveToRelative(
                        dx1 = -5.6f,
                        dy1 = 7.55f,
                        dx2 = -9.42f,
                        dy2 = 11.28f,
                        dx3 = -16.0f,
                        dy3 = 18.0f,
                    )
                    // c -8.8 8.98 -13.96 13.91 -24 21.5
                    curveToRelative(
                        dx1 = -8.8f,
                        dy1 = 8.98f,
                        dx2 = -13.96f,
                        dy2 = 13.91f,
                        dx3 = -24.0f,
                        dy3 = 21.5f,
                    )
                    // c -7.65 5.77 -12.23 8.64 -20.5 13.5
                    curveToRelative(
                        dx1 = -7.65f,
                        dy1 = 5.77f,
                        dx2 = -12.23f,
                        dy2 = 8.64f,
                        dx3 = -20.5f,
                        dy3 = 13.5f,
                    )
                    // c -14.7 8.61 -23.3 13.22 -39.5 18.5
                    curveToRelative(
                        dx1 = -14.7f,
                        dy1 = 8.61f,
                        dx2 = -23.3f,
                        dy2 = 13.22f,
                        dx3 = -39.5f,
                        dy3 = 18.5f,
                    )
                }
                // M48 661.1 c-1.07 -16.2 -.2 -25.5 2.5 -41.5 3.3 -19.56 14.86 -48.55 14.86 -48.55 L125 752.59 l-32.5 52 s-9.45 .19 -15.5 0 c-8.22 -.24 -13.05 .59 -21 -1.5 -6.83 -1.78 -10.41 -3.37 -15 -10.5 -5.39 -8.16 -2.81 -15.3 -1.5 -25 2.5 -18.5 31 -31.84 33.5 -29.5 0 0 -22.91 -45.45 -25 -77
                path(
                    fill = SolidColor(theme.secondaryContainer),
                ) {
                    // M 48 661.1
                    moveTo(x = 48.0f, y = 661.1f)
                    // c -1.07 -16.2 -0.2 -25.5 2.5 -41.5
                    curveToRelative(
                        dx1 = -1.07f,
                        dy1 = -16.2f,
                        dx2 = -0.2f,
                        dy2 = -25.5f,
                        dx3 = 2.5f,
                        dy3 = -41.5f,
                    )
                    // c 3.3 -19.56 14.86 -48.55 14.86 -48.55
                    curveToRelative(
                        dx1 = 3.3f,
                        dy1 = -19.56f,
                        dx2 = 14.86f,
                        dy2 = -48.55f,
                        dx3 = 14.86f,
                        dy3 = -48.55f,
                    )
                    // L 125 752.59
                    lineTo(x = 125.0f, y = 752.59f)
                    // l -32.5 52
                    lineToRelative(dx = -32.5f, dy = 52.0f)
                    // s -9.45 0.19 -15.5 0
                    reflectiveCurveToRelative(
                        dx1 = -9.45f,
                        dy1 = 0.19f,
                        dx2 = -15.5f,
                        dy2 = 0.0f,
                    )
                    // c -8.22 -0.24 -13.05 0.59 -21 -1.5
                    curveToRelative(
                        dx1 = -8.22f,
                        dy1 = -0.24f,
                        dx2 = -13.05f,
                        dy2 = 0.59f,
                        dx3 = -21.0f,
                        dy3 = -1.5f,
                    )
                    // c -6.83 -1.78 -10.41 -3.37 -15 -10.5
                    curveToRelative(
                        dx1 = -6.83f,
                        dy1 = -1.78f,
                        dx2 = -10.41f,
                        dy2 = -3.37f,
                        dx3 = -15.0f,
                        dy3 = -10.5f,
                    )
                    // c -5.39 -8.16 -2.81 -15.3 -1.5 -25
                    curveToRelative(
                        dx1 = -5.39f,
                        dy1 = -8.16f,
                        dx2 = -2.81f,
                        dy2 = -15.3f,
                        dx3 = -1.5f,
                        dy3 = -25.0f,
                    )
                    // c 2.5 -18.5 31 -31.84 33.5 -29.5
                    curveToRelative(
                        dx1 = 2.5f,
                        dy1 = -18.5f,
                        dx2 = 31.0f,
                        dy2 = -31.84f,
                        dx3 = 33.5f,
                        dy3 = -29.5f,
                    )
                    // c 0 0 -22.91 -45.45 -25 -77
                    curveToRelative(
                        dx1 = 0.0f,
                        dy1 = 0.0f,
                        dx2 = -22.91f,
                        dy2 = -45.45f,
                        dx3 = -25.0f,
                        dy3 = -77.0f,
                    )
                }
                // M57.5 763.6 s-2.69 5.67 -3.8 12 c-1.14 6.53 -.7 10.4 0 17 .39 3.54 2.5 9 2.5 9
                path(
                    stroke = SolidColor(theme.secondary),
                    strokeLineCap = StrokeCap.Round,
                    strokeLineWidth = 7.0f,
                ) {
                    // M 57.5 763.6
                    moveTo(x = 57.5f, y = 763.6f)
                    // s -2.69 5.67 -3.8 12
                    reflectiveCurveToRelative(
                        dx1 = -2.69f,
                        dy1 = 5.67f,
                        dx2 = -3.8f,
                        dy2 = 12.0f,
                    )
                    // c -1.14 6.53 -0.7 10.4 0 17
                    curveToRelative(
                        dx1 = -1.14f,
                        dy1 = 6.53f,
                        dx2 = -0.7f,
                        dy2 = 10.4f,
                        dx3 = 0.0f,
                        dy3 = 17.0f,
                    )
                    // c 0.39 3.54 2.5 9 2.5 9
                    curveToRelative(
                        dx1 = 0.39f,
                        dy1 = 3.54f,
                        dx2 = 2.5f,
                        dy2 = 9.0f,
                        dx3 = 2.5f,
                        dy3 = 9.0f,
                    )
                }
                // M58 478.1 c2.11 -27.34 12.5 -70 12.5 -70 l327 -25 7.5 19.5 s-2.43 9.69 -3.5 16 c-1.85 10.82 -2.22 17.02 -2.5 28 a154 154 0 0 0 0 11.5 c.19 4.89 .2 7.66 1 12.5 1.37 8.28 3.07 12.83 6.5 20.5 3.83 8.55 12.5 20.5 12.5 20.5 -.46 .53 19.54 22.3 33 35.5 11.35 11.12 17.54 17.61 27.5 30 5.95 7.39 9.53 11.41 14.5 19.5 6.87 11.16 9.9 18.05 14 30.5 4.3 13.05 5.57 20.82 7 34.5 .84 7.97 .82 12.48 1 20.5 .15 6.83 .62 10.69 0 17.5 -.66 7.32 -1.83 11.33 -3.5 18.5 -2.84 12.14 -4.65 19 -9.5 30.5 -5.2 12.31 -12.6 25.7 -16.5 30 -3.9 4.29 -5.34 6.94 -9.5 11 -5.42 5.28 -14.71 9.88 -16 10.5 s-18.83 3.93 -20.5 4.5 -55.15 1.52 -73 1 c-.98 -.03 -.76 .86 -1.5 1.5 -1.8 1.55 -5.5 1 -6 1 s-6.5 -2 -6.5 -2 l-10 -2.5 s-10.12 -6.58 -10 -7.5 c.12 -.93 -2.3 -19.35 1 -24 6.72 -10.96 8.32 -11.3 13.5 -15.5 7.28 -5.9 16 -8.27 18 -9 3.43 -1.27 18.5 .1 19 -.5 0 0 4.6 .1 5.5 -2 1.09 -2.56 -2.3 -3.81 -4.5 -5.5 -3.98 -3.07 -11.08 -1.7 -12.5 -3 -1.42 -1.32 -17.4 3.64 -17.5 1.5 s-6.97 -12.95 -9 -22 c-1.8 -8.03 -1.53 -12.78 -1.5 -21 .05 -12 3.5 -30.5 3.5 -30.5 s3.54 -16.08 7 -26 c4.22 -12.14 13.91 -29.29 14 -29.5 s9.36 -12.16 14.5 -20.5 c7.62 -12.36 20.87 -13.43 28 -20.5 2.22 -2.2 1.01 -4.12 -1 -6.5 -10.5 -1.05 -15.22 1.1 -27 10.5 -12.04 9.6 -16.97 17.36 -25 30.5 -5.88 9.6 -13 25.98 -12.5 26 l-12 47 c-11.37 45.81 -26.58 108.6 -27.5 108.5 0 0 -9.37 18.22 -12 19.5 -2.63 1.27 -11.49 4.9 -18 5 -2.28 .03 -2.72 2.43 -5 2.5 -2.18 .05 -2.82 -2.54 -5 -2.5 -8.36 .13 -14.26 .56 -22 0 -2.35 -.18 -3.14 2.5 -5.5 2.5 s-3.35 -1.53 -5.5 -2.5 c-5.09 -2.33 -8.89 -1.9 -13 -6 -4.24 -4.23 -5.9 -7.38 -6 -13.5 -.14 -8.24 1.05 -13.08 5 -20 3.15 -5.53 7.5 -6.7 11 -12 v-56 s3 -43.5 5 -70.5 v-.02 c.1 -1.35 .13 -1.78 -.5 -2.99 -.66 -1.24 -2.5 -2.5 -2.5 -2.5 h-54 c-4.4 2.31 -3.05 5.53 -3 10 .26 22.92 6.5 35 8 58 2.05 31.38 1.5 46 1.5 77 0 3.5 -.51 9.04 -1.5 12.5 -1.73 6.07 -2.27 9.91 -6 15 -3.35 4.58 -11 9.5 -11 9.5 l-17.5 1 -46 -.5 -5.5 -.5 -5 -3.5 c-1.43 -.56 -10.37 -10.82 -9 -19.5 2.86 -18.1 7.98 -20.38 21.5 -38.5 0 0 -11.76 -41.93 -18.5 -69 -11.06 -44.4 -19.48 -69.06 -25 -114.5 -3.96 -32.6 -7.03 -51.24 -4.5 -84
                path(
                    fill = SolidColor(theme.surface),
                ) {
                    // M 58 478.1
                    moveTo(x = 58.0f, y = 478.1f)
                    // c 2.11 -27.34 12.5 -70 12.5 -70
                    curveToRelative(
                        dx1 = 2.11f,
                        dy1 = -27.34f,
                        dx2 = 12.5f,
                        dy2 = -70.0f,
                        dx3 = 12.5f,
                        dy3 = -70.0f,
                    )
                    // l 327 -25
                    lineToRelative(dx = 327.0f, dy = -25.0f)
                    // l 7.5 19.5
                    lineToRelative(dx = 7.5f, dy = 19.5f)
                    // s -2.43 9.69 -3.5 16
                    reflectiveCurveToRelative(
                        dx1 = -2.43f,
                        dy1 = 9.69f,
                        dx2 = -3.5f,
                        dy2 = 16.0f,
                    )
                    // c -1.85 10.82 -2.22 17.02 -2.5 28
                    curveToRelative(
                        dx1 = -1.85f,
                        dy1 = 10.82f,
                        dx2 = -2.22f,
                        dy2 = 17.02f,
                        dx3 = -2.5f,
                        dy3 = 28.0f,
                    )
                    // a 154 154 0 0 0 0 11.5
                    arcToRelative(
                        a = 154.0f,
                        b = 154.0f,
                        theta = 0.0f,
                        isMoreThanHalf = false,
                        isPositiveArc = false,
                        dx1 = 0.0f,
                        dy1 = 11.5f,
                    )
                    // c 0.19 4.89 0.2 7.66 1 12.5
                    curveToRelative(
                        dx1 = 0.19f,
                        dy1 = 4.89f,
                        dx2 = 0.2f,
                        dy2 = 7.66f,
                        dx3 = 1.0f,
                        dy3 = 12.5f,
                    )
                    // c 1.37 8.28 3.07 12.83 6.5 20.5
                    curveToRelative(
                        dx1 = 1.37f,
                        dy1 = 8.28f,
                        dx2 = 3.07f,
                        dy2 = 12.83f,
                        dx3 = 6.5f,
                        dy3 = 20.5f,
                    )
                    // c 3.83 8.55 12.5 20.5 12.5 20.5
                    curveToRelative(
                        dx1 = 3.83f,
                        dy1 = 8.55f,
                        dx2 = 12.5f,
                        dy2 = 20.5f,
                        dx3 = 12.5f,
                        dy3 = 20.5f,
                    )
                    // c -0.46 0.53 19.54 22.3 33 35.5
                    curveToRelative(
                        dx1 = -0.46f,
                        dy1 = 0.53f,
                        dx2 = 19.54f,
                        dy2 = 22.3f,
                        dx3 = 33.0f,
                        dy3 = 35.5f,
                    )
                    // c 11.35 11.12 17.54 17.61 27.5 30
                    curveToRelative(
                        dx1 = 11.35f,
                        dy1 = 11.12f,
                        dx2 = 17.54f,
                        dy2 = 17.61f,
                        dx3 = 27.5f,
                        dy3 = 30.0f,
                    )
                    // c 5.95 7.39 9.53 11.41 14.5 19.5
                    curveToRelative(
                        dx1 = 5.95f,
                        dy1 = 7.39f,
                        dx2 = 9.53f,
                        dy2 = 11.41f,
                        dx3 = 14.5f,
                        dy3 = 19.5f,
                    )
                    // c 6.87 11.16 9.9 18.05 14 30.5
                    curveToRelative(
                        dx1 = 6.87f,
                        dy1 = 11.16f,
                        dx2 = 9.9f,
                        dy2 = 18.05f,
                        dx3 = 14.0f,
                        dy3 = 30.5f,
                    )
                    // c 4.3 13.05 5.57 20.82 7 34.5
                    curveToRelative(
                        dx1 = 4.3f,
                        dy1 = 13.05f,
                        dx2 = 5.57f,
                        dy2 = 20.82f,
                        dx3 = 7.0f,
                        dy3 = 34.5f,
                    )
                    // c 0.84 7.97 0.82 12.48 1 20.5
                    curveToRelative(
                        dx1 = 0.84f,
                        dy1 = 7.97f,
                        dx2 = 0.82f,
                        dy2 = 12.48f,
                        dx3 = 1.0f,
                        dy3 = 20.5f,
                    )
                    // c 0.15 6.83 0.62 10.69 0 17.5
                    curveToRelative(
                        dx1 = 0.15f,
                        dy1 = 6.83f,
                        dx2 = 0.62f,
                        dy2 = 10.69f,
                        dx3 = 0.0f,
                        dy3 = 17.5f,
                    )
                    // c -0.66 7.32 -1.83 11.33 -3.5 18.5
                    curveToRelative(
                        dx1 = -0.66f,
                        dy1 = 7.32f,
                        dx2 = -1.83f,
                        dy2 = 11.33f,
                        dx3 = -3.5f,
                        dy3 = 18.5f,
                    )
                    // c -2.84 12.14 -4.65 19 -9.5 30.5
                    curveToRelative(
                        dx1 = -2.84f,
                        dy1 = 12.14f,
                        dx2 = -4.65f,
                        dy2 = 19.0f,
                        dx3 = -9.5f,
                        dy3 = 30.5f,
                    )
                    // c -5.2 12.31 -12.6 25.7 -16.5 30
                    curveToRelative(
                        dx1 = -5.2f,
                        dy1 = 12.31f,
                        dx2 = -12.6f,
                        dy2 = 25.7f,
                        dx3 = -16.5f,
                        dy3 = 30.0f,
                    )
                    // c -3.9 4.29 -5.34 6.94 -9.5 11
                    curveToRelative(
                        dx1 = -3.9f,
                        dy1 = 4.29f,
                        dx2 = -5.34f,
                        dy2 = 6.94f,
                        dx3 = -9.5f,
                        dy3 = 11.0f,
                    )
                    // c -5.42 5.28 -14.71 9.88 -16 10.5
                    curveToRelative(
                        dx1 = -5.42f,
                        dy1 = 5.28f,
                        dx2 = -14.71f,
                        dy2 = 9.88f,
                        dx3 = -16.0f,
                        dy3 = 10.5f,
                    )
                    // s -18.83 3.93 -20.5 4.5
                    reflectiveCurveToRelative(
                        dx1 = -18.83f,
                        dy1 = 3.93f,
                        dx2 = -20.5f,
                        dy2 = 4.5f,
                    )
                    // s -55.15 1.52 -73 1
                    reflectiveCurveToRelative(
                        dx1 = -55.15f,
                        dy1 = 1.52f,
                        dx2 = -73.0f,
                        dy2 = 1.0f,
                    )
                    // c -0.98 -0.03 -0.76 0.86 -1.5 1.5
                    curveToRelative(
                        dx1 = -0.98f,
                        dy1 = -0.03f,
                        dx2 = -0.76f,
                        dy2 = 0.86f,
                        dx3 = -1.5f,
                        dy3 = 1.5f,
                    )
                    // c -1.8 1.55 -5.5 1 -6 1
                    curveToRelative(
                        dx1 = -1.8f,
                        dy1 = 1.55f,
                        dx2 = -5.5f,
                        dy2 = 1.0f,
                        dx3 = -6.0f,
                        dy3 = 1.0f,
                    )
                    // s -6.5 -2 -6.5 -2
                    reflectiveCurveToRelative(
                        dx1 = -6.5f,
                        dy1 = -2.0f,
                        dx2 = -6.5f,
                        dy2 = -2.0f,
                    )
                    // l -10 -2.5
                    lineToRelative(dx = -10.0f, dy = -2.5f)
                    // s -10.12 -6.58 -10 -7.5
                    reflectiveCurveToRelative(
                        dx1 = -10.12f,
                        dy1 = -6.58f,
                        dx2 = -10.0f,
                        dy2 = -7.5f,
                    )
                    // c 0.12 -0.93 -2.3 -19.35 1 -24
                    curveToRelative(
                        dx1 = 0.12f,
                        dy1 = -0.93f,
                        dx2 = -2.3f,
                        dy2 = -19.35f,
                        dx3 = 1.0f,
                        dy3 = -24.0f,
                    )
                    // c 6.72 -10.96 8.32 -11.3 13.5 -15.5
                    curveToRelative(
                        dx1 = 6.72f,
                        dy1 = -10.96f,
                        dx2 = 8.32f,
                        dy2 = -11.3f,
                        dx3 = 13.5f,
                        dy3 = -15.5f,
                    )
                    // c 7.28 -5.9 16 -8.27 18 -9
                    curveToRelative(
                        dx1 = 7.28f,
                        dy1 = -5.9f,
                        dx2 = 16.0f,
                        dy2 = -8.27f,
                        dx3 = 18.0f,
                        dy3 = -9.0f,
                    )
                    // c 3.43 -1.27 18.5 0.1 19 -0.5
                    curveToRelative(
                        dx1 = 3.43f,
                        dy1 = -1.27f,
                        dx2 = 18.5f,
                        dy2 = 0.1f,
                        dx3 = 19.0f,
                        dy3 = -0.5f,
                    )
                    // c 0 0 4.6 0.1 5.5 -2
                    curveToRelative(
                        dx1 = 0.0f,
                        dy1 = 0.0f,
                        dx2 = 4.6f,
                        dy2 = 0.1f,
                        dx3 = 5.5f,
                        dy3 = -2.0f,
                    )
                    // c 1.09 -2.56 -2.3 -3.81 -4.5 -5.5
                    curveToRelative(
                        dx1 = 1.09f,
                        dy1 = -2.56f,
                        dx2 = -2.3f,
                        dy2 = -3.81f,
                        dx3 = -4.5f,
                        dy3 = -5.5f,
                    )
                    // c -3.98 -3.07 -11.08 -1.7 -12.5 -3
                    curveToRelative(
                        dx1 = -3.98f,
                        dy1 = -3.07f,
                        dx2 = -11.08f,
                        dy2 = -1.7f,
                        dx3 = -12.5f,
                        dy3 = -3.0f,
                    )
                    // c -1.42 -1.32 -17.4 3.64 -17.5 1.5
                    curveToRelative(
                        dx1 = -1.42f,
                        dy1 = -1.32f,
                        dx2 = -17.4f,
                        dy2 = 3.64f,
                        dx3 = -17.5f,
                        dy3 = 1.5f,
                    )
                    // s -6.97 -12.95 -9 -22
                    reflectiveCurveToRelative(
                        dx1 = -6.97f,
                        dy1 = -12.95f,
                        dx2 = -9.0f,
                        dy2 = -22.0f,
                    )
                    // c -1.8 -8.03 -1.53 -12.78 -1.5 -21
                    curveToRelative(
                        dx1 = -1.8f,
                        dy1 = -8.03f,
                        dx2 = -1.53f,
                        dy2 = -12.78f,
                        dx3 = -1.5f,
                        dy3 = -21.0f,
                    )
                    // c 0.05 -12 3.5 -30.5 3.5 -30.5
                    curveToRelative(
                        dx1 = 0.05f,
                        dy1 = -12.0f,
                        dx2 = 3.5f,
                        dy2 = -30.5f,
                        dx3 = 3.5f,
                        dy3 = -30.5f,
                    )
                    // s 3.54 -16.08 7 -26
                    reflectiveCurveToRelative(
                        dx1 = 3.54f,
                        dy1 = -16.08f,
                        dx2 = 7.0f,
                        dy2 = -26.0f,
                    )
                    // c 4.22 -12.14 13.91 -29.29 14 -29.5
                    curveToRelative(
                        dx1 = 4.22f,
                        dy1 = -12.14f,
                        dx2 = 13.91f,
                        dy2 = -29.29f,
                        dx3 = 14.0f,
                        dy3 = -29.5f,
                    )
                    // s 9.36 -12.16 14.5 -20.5
                    reflectiveCurveToRelative(
                        dx1 = 9.36f,
                        dy1 = -12.16f,
                        dx2 = 14.5f,
                        dy2 = -20.5f,
                    )
                    // c 7.62 -12.36 20.87 -13.43 28 -20.5
                    curveToRelative(
                        dx1 = 7.62f,
                        dy1 = -12.36f,
                        dx2 = 20.87f,
                        dy2 = -13.43f,
                        dx3 = 28.0f,
                        dy3 = -20.5f,
                    )
                    // c 2.22 -2.2 1.01 -4.12 -1 -6.5
                    curveToRelative(
                        dx1 = 2.22f,
                        dy1 = -2.2f,
                        dx2 = 1.01f,
                        dy2 = -4.12f,
                        dx3 = -1.0f,
                        dy3 = -6.5f,
                    )
                    // c -10.5 -1.05 -15.22 1.1 -27 10.5
                    curveToRelative(
                        dx1 = -10.5f,
                        dy1 = -1.05f,
                        dx2 = -15.22f,
                        dy2 = 1.1f,
                        dx3 = -27.0f,
                        dy3 = 10.5f,
                    )
                    // c -12.04 9.6 -16.97 17.36 -25 30.5
                    curveToRelative(
                        dx1 = -12.04f,
                        dy1 = 9.6f,
                        dx2 = -16.97f,
                        dy2 = 17.36f,
                        dx3 = -25.0f,
                        dy3 = 30.5f,
                    )
                    // c -5.88 9.6 -13 25.98 -12.5 26
                    curveToRelative(
                        dx1 = -5.88f,
                        dy1 = 9.6f,
                        dx2 = -13.0f,
                        dy2 = 25.98f,
                        dx3 = -12.5f,
                        dy3 = 26.0f,
                    )
                    // l -12 47
                    lineToRelative(dx = -12.0f, dy = 47.0f)
                    // c -11.37 45.81 -26.58 108.6 -27.5 108.5
                    curveToRelative(
                        dx1 = -11.37f,
                        dy1 = 45.81f,
                        dx2 = -26.58f,
                        dy2 = 108.6f,
                        dx3 = -27.5f,
                        dy3 = 108.5f,
                    )
                    // c 0 0 -9.37 18.22 -12 19.5
                    curveToRelative(
                        dx1 = 0.0f,
                        dy1 = 0.0f,
                        dx2 = -9.37f,
                        dy2 = 18.22f,
                        dx3 = -12.0f,
                        dy3 = 19.5f,
                    )
                    // c -2.63 1.27 -11.49 4.9 -18 5
                    curveToRelative(
                        dx1 = -2.63f,
                        dy1 = 1.27f,
                        dx2 = -11.49f,
                        dy2 = 4.9f,
                        dx3 = -18.0f,
                        dy3 = 5.0f,
                    )
                    // c -2.28 0.03 -2.72 2.43 -5 2.5
                    curveToRelative(
                        dx1 = -2.28f,
                        dy1 = 0.03f,
                        dx2 = -2.72f,
                        dy2 = 2.43f,
                        dx3 = -5.0f,
                        dy3 = 2.5f,
                    )
                    // c -2.18 0.05 -2.82 -2.54 -5 -2.5
                    curveToRelative(
                        dx1 = -2.18f,
                        dy1 = 0.05f,
                        dx2 = -2.82f,
                        dy2 = -2.54f,
                        dx3 = -5.0f,
                        dy3 = -2.5f,
                    )
                    // c -8.36 0.13 -14.26 0.56 -22 0
                    curveToRelative(
                        dx1 = -8.36f,
                        dy1 = 0.13f,
                        dx2 = -14.26f,
                        dy2 = 0.56f,
                        dx3 = -22.0f,
                        dy3 = 0.0f,
                    )
                    // c -2.35 -0.18 -3.14 2.5 -5.5 2.5
                    curveToRelative(
                        dx1 = -2.35f,
                        dy1 = -0.18f,
                        dx2 = -3.14f,
                        dy2 = 2.5f,
                        dx3 = -5.5f,
                        dy3 = 2.5f,
                    )
                    // s -3.35 -1.53 -5.5 -2.5
                    reflectiveCurveToRelative(
                        dx1 = -3.35f,
                        dy1 = -1.53f,
                        dx2 = -5.5f,
                        dy2 = -2.5f,
                    )
                    // c -5.09 -2.33 -8.89 -1.9 -13 -6
                    curveToRelative(
                        dx1 = -5.09f,
                        dy1 = -2.33f,
                        dx2 = -8.89f,
                        dy2 = -1.9f,
                        dx3 = -13.0f,
                        dy3 = -6.0f,
                    )
                    // c -4.24 -4.23 -5.9 -7.38 -6 -13.5
                    curveToRelative(
                        dx1 = -4.24f,
                        dy1 = -4.23f,
                        dx2 = -5.9f,
                        dy2 = -7.38f,
                        dx3 = -6.0f,
                        dy3 = -13.5f,
                    )
                    // c -0.14 -8.24 1.05 -13.08 5 -20
                    curveToRelative(
                        dx1 = -0.14f,
                        dy1 = -8.24f,
                        dx2 = 1.05f,
                        dy2 = -13.08f,
                        dx3 = 5.0f,
                        dy3 = -20.0f,
                    )
                    // c 3.15 -5.53 7.5 -6.7 11 -12
                    curveToRelative(
                        dx1 = 3.15f,
                        dy1 = -5.53f,
                        dx2 = 7.5f,
                        dy2 = -6.7f,
                        dx3 = 11.0f,
                        dy3 = -12.0f,
                    )
                    // v -56
                    verticalLineToRelative(dy = -56.0f)
                    // s 3 -43.5 5 -70.5
                    reflectiveCurveToRelative(
                        dx1 = 3.0f,
                        dy1 = -43.5f,
                        dx2 = 5.0f,
                        dy2 = -70.5f,
                    )
                    // v -0.02
                    verticalLineToRelative(dy = -0.02f)
                    // c 0.1 -1.35 0.13 -1.78 -0.5 -2.99
                    curveToRelative(
                        dx1 = 0.1f,
                        dy1 = -1.35f,
                        dx2 = 0.13f,
                        dy2 = -1.78f,
                        dx3 = -0.5f,
                        dy3 = -2.99f,
                    )
                    // c -0.66 -1.24 -2.5 -2.5 -2.5 -2.5
                    curveToRelative(
                        dx1 = -0.66f,
                        dy1 = -1.24f,
                        dx2 = -2.5f,
                        dy2 = -2.5f,
                        dx3 = -2.5f,
                        dy3 = -2.5f,
                    )
                    // h -54
                    horizontalLineToRelative(dx = -54.0f)
                    // c -4.4 2.31 -3.05 5.53 -3 10
                    curveToRelative(
                        dx1 = -4.4f,
                        dy1 = 2.31f,
                        dx2 = -3.05f,
                        dy2 = 5.53f,
                        dx3 = -3.0f,
                        dy3 = 10.0f,
                    )
                    // c 0.26 22.92 6.5 35 8 58
                    curveToRelative(
                        dx1 = 0.26f,
                        dy1 = 22.92f,
                        dx2 = 6.5f,
                        dy2 = 35.0f,
                        dx3 = 8.0f,
                        dy3 = 58.0f,
                    )
                    // c 2.05 31.38 1.5 46 1.5 77
                    curveToRelative(
                        dx1 = 2.05f,
                        dy1 = 31.38f,
                        dx2 = 1.5f,
                        dy2 = 46.0f,
                        dx3 = 1.5f,
                        dy3 = 77.0f,
                    )
                    // c 0 3.5 -0.51 9.04 -1.5 12.5
                    curveToRelative(
                        dx1 = 0.0f,
                        dy1 = 3.5f,
                        dx2 = -0.51f,
                        dy2 = 9.04f,
                        dx3 = -1.5f,
                        dy3 = 12.5f,
                    )
                    // c -1.73 6.07 -2.27 9.91 -6 15
                    curveToRelative(
                        dx1 = -1.73f,
                        dy1 = 6.07f,
                        dx2 = -2.27f,
                        dy2 = 9.91f,
                        dx3 = -6.0f,
                        dy3 = 15.0f,
                    )
                    // c -3.35 4.58 -11 9.5 -11 9.5
                    curveToRelative(
                        dx1 = -3.35f,
                        dy1 = 4.58f,
                        dx2 = -11.0f,
                        dy2 = 9.5f,
                        dx3 = -11.0f,
                        dy3 = 9.5f,
                    )
                    // l -17.5 1
                    lineToRelative(dx = -17.5f, dy = 1.0f)
                    // l -46 -0.5
                    lineToRelative(dx = -46.0f, dy = -0.5f)
                    // l -5.5 -0.5
                    lineToRelative(dx = -5.5f, dy = -0.5f)
                    // l -5 -3.5
                    lineToRelative(dx = -5.0f, dy = -3.5f)
                    // c -1.43 -0.56 -10.37 -10.82 -9 -19.5
                    curveToRelative(
                        dx1 = -1.43f,
                        dy1 = -0.56f,
                        dx2 = -10.37f,
                        dy2 = -10.82f,
                        dx3 = -9.0f,
                        dy3 = -19.5f,
                    )
                    // c 2.86 -18.1 7.98 -20.38 21.5 -38.5
                    curveToRelative(
                        dx1 = 2.86f,
                        dy1 = -18.1f,
                        dx2 = 7.98f,
                        dy2 = -20.38f,
                        dx3 = 21.5f,
                        dy3 = -38.5f,
                    )
                    // c 0 0 -11.76 -41.93 -18.5 -69
                    curveToRelative(
                        dx1 = 0.0f,
                        dy1 = 0.0f,
                        dx2 = -11.76f,
                        dy2 = -41.93f,
                        dx3 = -18.5f,
                        dy3 = -69.0f,
                    )
                    // c -11.06 -44.4 -19.48 -69.06 -25 -114.5
                    curveToRelative(
                        dx1 = -11.06f,
                        dy1 = -44.4f,
                        dx2 = -19.48f,
                        dy2 = -69.06f,
                        dx3 = -25.0f,
                        dy3 = -114.5f,
                    )
                    // c -3.96 -32.6 -7.03 -51.24 -4.5 -84
                    curveToRelative(
                        dx1 = -3.96f,
                        dy1 = -32.6f,
                        dx2 = -7.03f,
                        dy2 = -51.24f,
                        dx3 = -4.5f,
                        dy3 = -84.0f,
                    )
                }
                // m70 409.6 8.5 -29.5 99 38.5 s-44.33 5.6 -63.5 3 c-17.38 -2.36 -44 -12 -44 -12
                path(
                    fill = SolidColor(theme.primaryContainer),
                ) {
                    // M 70 409.6
                    moveTo(x = 70.0f, y = 409.6f)
                    // l 8.5 -29.5
                    lineToRelative(dx = 8.5f, dy = -29.5f)
                    // l 99 38.5
                    lineToRelative(dx = 99.0f, dy = 38.5f)
                    // s -44.33 5.6 -63.5 3
                    reflectiveCurveToRelative(
                        dx1 = -44.33f,
                        dy1 = 5.6f,
                        dx2 = -63.5f,
                        dy2 = 3.0f,
                    )
                    // c -17.38 -2.36 -44 -12 -44 -12
                    curveToRelative(
                        dx1 = -17.38f,
                        dy1 = -2.36f,
                        dx2 = -44.0f,
                        dy2 = -12.0f,
                        dx3 = -44.0f,
                        dy3 = -12.0f,
                    )
                }
                // M423 437.6 c-7.99 -12.93 -18 -35.5 -18 -35.5 s-5.09 16.78 -6.5 27.5 c-1.89 14.32 -1.94 22.75 .5 37 1.47 8.59 3.2 13.36 6.5 21.5 4.99 12.32 9.41 18.77 17.5 29.5 7 9.28 21 22 21 22 14.16 15.5 24.1 21.88 36 39 7.14 10.25 11.46 14.71 17 26 6.26 12.74 10.45 19.72 13.5 33.5 2.42 10.9 4.08 16.26 4.5 27.5 .39 10.35 0 26.5 0 26.5 l-2 18.5 s-3.1 18.32 -7 29.5 c-4.3 12.28 -7.6 18.96 -14.5 30 -4.88 7.81 -14.5 19.5 -14.5 19.5 s10.35 -7.48 15.5 -12.5 c6.14 -6 9.59 -9.48 14.5 -16.5 5.2 -7.45 7.28 -12.22 11 -20.5 5.31 -11.83 7.37 -18.92 10.5 -31.5 l.09 -.36 c2.62 -10.56 4.15 -16.69 4.91 -27.65 .5 -7.2 .11 -11.27 0 -18.5 -.2 -13.29 .3 -20.83 -1.5 -34 -1.6 -11.68 -3.4 -18.11 -6.5 -29.5 -3.57 -13.1 -5.91 -20.4 -11 -33 -3.37 -8.34 -5.31 -13.03 -9.5 -21 -5.47 -10.4 -9.27 -15.86 -16 -25.5 -8.28 -11.85 -23 -29 -23 -29 s-17.41 -18.32 -27.5 -31.5 c-6.06 -7.9 -10.2 -12.42 -15.5 -21 m-18 -35.5 -7.5 -22.5 -102.5 39 s44.33 5.6 63.5 3 c17.38 -2.36 46.5 -19.5 46.5 -19.5
                path(
                    fill = SolidColor(theme.secondaryContainer),
                ) {
                    // M 423 437.6
                    moveTo(x = 423.0f, y = 437.6f)
                    // c -7.99 -12.93 -18 -35.5 -18 -35.5
                    curveToRelative(
                        dx1 = -7.99f,
                        dy1 = -12.93f,
                        dx2 = -18.0f,
                        dy2 = -35.5f,
                        dx3 = -18.0f,
                        dy3 = -35.5f,
                    )
                    // s -5.09 16.78 -6.5 27.5
                    reflectiveCurveToRelative(
                        dx1 = -5.09f,
                        dy1 = 16.78f,
                        dx2 = -6.5f,
                        dy2 = 27.5f,
                    )
                    // c -1.89 14.32 -1.94 22.75 0.5 37
                    curveToRelative(
                        dx1 = -1.89f,
                        dy1 = 14.32f,
                        dx2 = -1.94f,
                        dy2 = 22.75f,
                        dx3 = 0.5f,
                        dy3 = 37.0f,
                    )
                    // c 1.47 8.59 3.2 13.36 6.5 21.5
                    curveToRelative(
                        dx1 = 1.47f,
                        dy1 = 8.59f,
                        dx2 = 3.2f,
                        dy2 = 13.36f,
                        dx3 = 6.5f,
                        dy3 = 21.5f,
                    )
                    // c 4.99 12.32 9.41 18.77 17.5 29.5
                    curveToRelative(
                        dx1 = 4.99f,
                        dy1 = 12.32f,
                        dx2 = 9.41f,
                        dy2 = 18.77f,
                        dx3 = 17.5f,
                        dy3 = 29.5f,
                    )
                    // c 7 9.28 21 22 21 22
                    curveToRelative(
                        dx1 = 7.0f,
                        dy1 = 9.28f,
                        dx2 = 21.0f,
                        dy2 = 22.0f,
                        dx3 = 21.0f,
                        dy3 = 22.0f,
                    )
                    // c 14.16 15.5 24.1 21.88 36 39
                    curveToRelative(
                        dx1 = 14.16f,
                        dy1 = 15.5f,
                        dx2 = 24.1f,
                        dy2 = 21.88f,
                        dx3 = 36.0f,
                        dy3 = 39.0f,
                    )
                    // c 7.14 10.25 11.46 14.71 17 26
                    curveToRelative(
                        dx1 = 7.14f,
                        dy1 = 10.25f,
                        dx2 = 11.46f,
                        dy2 = 14.71f,
                        dx3 = 17.0f,
                        dy3 = 26.0f,
                    )
                    // c 6.26 12.74 10.45 19.72 13.5 33.5
                    curveToRelative(
                        dx1 = 6.26f,
                        dy1 = 12.74f,
                        dx2 = 10.45f,
                        dy2 = 19.72f,
                        dx3 = 13.5f,
                        dy3 = 33.5f,
                    )
                    // c 2.42 10.9 4.08 16.26 4.5 27.5
                    curveToRelative(
                        dx1 = 2.42f,
                        dy1 = 10.9f,
                        dx2 = 4.08f,
                        dy2 = 16.26f,
                        dx3 = 4.5f,
                        dy3 = 27.5f,
                    )
                    // c 0.39 10.35 0 26.5 0 26.5
                    curveToRelative(
                        dx1 = 0.39f,
                        dy1 = 10.35f,
                        dx2 = 0.0f,
                        dy2 = 26.5f,
                        dx3 = 0.0f,
                        dy3 = 26.5f,
                    )
                    // l -2 18.5
                    lineToRelative(dx = -2.0f, dy = 18.5f)
                    // s -3.1 18.32 -7 29.5
                    reflectiveCurveToRelative(
                        dx1 = -3.1f,
                        dy1 = 18.32f,
                        dx2 = -7.0f,
                        dy2 = 29.5f,
                    )
                    // c -4.3 12.28 -7.6 18.96 -14.5 30
                    curveToRelative(
                        dx1 = -4.3f,
                        dy1 = 12.28f,
                        dx2 = -7.6f,
                        dy2 = 18.96f,
                        dx3 = -14.5f,
                        dy3 = 30.0f,
                    )
                    // c -4.88 7.81 -14.5 19.5 -14.5 19.5
                    curveToRelative(
                        dx1 = -4.88f,
                        dy1 = 7.81f,
                        dx2 = -14.5f,
                        dy2 = 19.5f,
                        dx3 = -14.5f,
                        dy3 = 19.5f,
                    )
                    // s 10.35 -7.48 15.5 -12.5
                    reflectiveCurveToRelative(
                        dx1 = 10.35f,
                        dy1 = -7.48f,
                        dx2 = 15.5f,
                        dy2 = -12.5f,
                    )
                    // c 6.14 -6 9.59 -9.48 14.5 -16.5
                    curveToRelative(
                        dx1 = 6.14f,
                        dy1 = -6.0f,
                        dx2 = 9.59f,
                        dy2 = -9.48f,
                        dx3 = 14.5f,
                        dy3 = -16.5f,
                    )
                    // c 5.2 -7.45 7.28 -12.22 11 -20.5
                    curveToRelative(
                        dx1 = 5.2f,
                        dy1 = -7.45f,
                        dx2 = 7.28f,
                        dy2 = -12.22f,
                        dx3 = 11.0f,
                        dy3 = -20.5f,
                    )
                    // c 5.31 -11.83 7.37 -18.92 10.5 -31.5
                    curveToRelative(
                        dx1 = 5.31f,
                        dy1 = -11.83f,
                        dx2 = 7.37f,
                        dy2 = -18.92f,
                        dx3 = 10.5f,
                        dy3 = -31.5f,
                    )
                    // l 0.09 -0.36
                    lineToRelative(dx = 0.09f, dy = -0.36f)
                    // c 2.62 -10.56 4.15 -16.69 4.91 -27.65
                    curveToRelative(
                        dx1 = 2.62f,
                        dy1 = -10.56f,
                        dx2 = 4.15f,
                        dy2 = -16.69f,
                        dx3 = 4.91f,
                        dy3 = -27.65f,
                    )
                    // c 0.5 -7.2 0.11 -11.27 0 -18.5
                    curveToRelative(
                        dx1 = 0.5f,
                        dy1 = -7.2f,
                        dx2 = 0.11f,
                        dy2 = -11.27f,
                        dx3 = 0.0f,
                        dy3 = -18.5f,
                    )
                    // c -0.2 -13.29 0.3 -20.83 -1.5 -34
                    curveToRelative(
                        dx1 = -0.2f,
                        dy1 = -13.29f,
                        dx2 = 0.3f,
                        dy2 = -20.83f,
                        dx3 = -1.5f,
                        dy3 = -34.0f,
                    )
                    // c -1.6 -11.68 -3.4 -18.11 -6.5 -29.5
                    curveToRelative(
                        dx1 = -1.6f,
                        dy1 = -11.68f,
                        dx2 = -3.4f,
                        dy2 = -18.11f,
                        dx3 = -6.5f,
                        dy3 = -29.5f,
                    )
                    // c -3.57 -13.1 -5.91 -20.4 -11 -33
                    curveToRelative(
                        dx1 = -3.57f,
                        dy1 = -13.1f,
                        dx2 = -5.91f,
                        dy2 = -20.4f,
                        dx3 = -11.0f,
                        dy3 = -33.0f,
                    )
                    // c -3.37 -8.34 -5.31 -13.03 -9.5 -21
                    curveToRelative(
                        dx1 = -3.37f,
                        dy1 = -8.34f,
                        dx2 = -5.31f,
                        dy2 = -13.03f,
                        dx3 = -9.5f,
                        dy3 = -21.0f,
                    )
                    // c -5.47 -10.4 -9.27 -15.86 -16 -25.5
                    curveToRelative(
                        dx1 = -5.47f,
                        dy1 = -10.4f,
                        dx2 = -9.27f,
                        dy2 = -15.86f,
                        dx3 = -16.0f,
                        dy3 = -25.5f,
                    )
                    // c -8.28 -11.85 -23 -29 -23 -29
                    curveToRelative(
                        dx1 = -8.28f,
                        dy1 = -11.85f,
                        dx2 = -23.0f,
                        dy2 = -29.0f,
                        dx3 = -23.0f,
                        dy3 = -29.0f,
                    )
                    // s -17.41 -18.32 -27.5 -31.5
                    reflectiveCurveToRelative(
                        dx1 = -17.41f,
                        dy1 = -18.32f,
                        dx2 = -27.5f,
                        dy2 = -31.5f,
                    )
                    // c -6.06 -7.9 -10.2 -12.42 -15.5 -21
                    curveToRelative(
                        dx1 = -6.06f,
                        dy1 = -7.9f,
                        dx2 = -10.2f,
                        dy2 = -12.42f,
                        dx3 = -15.5f,
                        dy3 = -21.0f,
                    )
                    // m -18 -35.5
                    moveToRelative(dx = -18.0f, dy = -35.5f)
                    // l -7.5 -22.5
                    lineToRelative(dx = -7.5f, dy = -22.5f)
                    // l -102.5 39
                    lineToRelative(dx = -102.5f, dy = 39.0f)
                    // s 44.33 5.6 63.5 3
                    reflectiveCurveToRelative(
                        dx1 = 44.33f,
                        dy1 = 5.6f,
                        dx2 = 63.5f,
                        dy2 = 3.0f,
                    )
                    // c 17.38 -2.36 46.5 -19.5 46.5 -19.5
                    curveToRelative(
                        dx1 = 17.38f,
                        dy1 = -2.36f,
                        dx2 = 46.5f,
                        dy2 = -19.5f,
                        dx3 = 46.5f,
                        dy3 = -19.5f,
                    )
                }
                // M458 269.1 c0 96.37 -98.14 160 -215.5 160 S24 369.96 24 273.6 125.14 75.1 242.5 75.1 c53.5 0 105.46 7.78 143.5 39 45.4 37.25 72 102.56 72 155
                path(
                    fill = SolidColor(theme.surface),
                ) {
                    // M 458 269.1
                    moveTo(x = 458.0f, y = 269.1f)
                    // c 0 96.37 -98.14 160 -215.5 160
                    curveToRelative(
                        dx1 = 0.0f,
                        dy1 = 96.37f,
                        dx2 = -98.14f,
                        dy2 = 160.0f,
                        dx3 = -215.5f,
                        dy3 = 160.0f,
                    )
                    // S 24 369.96 24 273.6
                    reflectiveCurveTo(
                        x1 = 24.0f,
                        y1 = 369.96f,
                        x2 = 24.0f,
                        y2 = 273.6f,
                    )
                    // S 125.14 75.1 242.5 75.1
                    reflectiveCurveTo(
                        x1 = 125.14f,
                        y1 = 75.1f,
                        x2 = 242.5f,
                        y2 = 75.1f,
                    )
                    // c 53.5 0 105.46 7.78 143.5 39
                    curveToRelative(
                        dx1 = 53.5f,
                        dy1 = 0.0f,
                        dx2 = 105.46f,
                        dy2 = 7.78f,
                        dx3 = 143.5f,
                        dy3 = 39.0f,
                    )
                    // c 45.4 37.25 72 102.56 72 155
                    curveToRelative(
                        dx1 = 45.4f,
                        dy1 = 37.25f,
                        dx2 = 72.0f,
                        dy2 = 102.56f,
                        dx3 = 72.0f,
                        dy3 = 155.0f,
                    )
                }
                // M82.68 122.01 C64.41 138.23 39.5 193.6 39.5 193.6 c-3.85 -.25 -15.43 -74.6 -11 -131 2.55 -32.4 12.17 -58.88 27 -62 40.63 -8.52 123.63 77.5 124 80.5 0 0 -79.82 25.83 -96.82 40.92
                path(
                    fill = SolidColor(theme.primary),
                ) {
                    // M 82.68 122.01
                    moveTo(x = 82.68f, y = 122.01f)
                    // C 64.41 138.23 39.5 193.6 39.5 193.6
                    curveTo(
                        x1 = 64.41f,
                        y1 = 138.23f,
                        x2 = 39.5f,
                        y2 = 193.6f,
                        x3 = 39.5f,
                        y3 = 193.6f,
                    )
                    // c -3.85 -0.25 -15.43 -74.6 -11 -131
                    curveToRelative(
                        dx1 = -3.85f,
                        dy1 = -0.25f,
                        dx2 = -15.43f,
                        dy2 = -74.6f,
                        dx3 = -11.0f,
                        dy3 = -131.0f,
                    )
                    // c 2.55 -32.4 12.17 -58.88 27 -62
                    curveToRelative(
                        dx1 = 2.55f,
                        dy1 = -32.4f,
                        dx2 = 12.17f,
                        dy2 = -58.88f,
                        dx3 = 27.0f,
                        dy3 = -62.0f,
                    )
                    // c 40.63 -8.52 123.63 77.5 124 80.5
                    curveToRelative(
                        dx1 = 40.63f,
                        dy1 = -8.52f,
                        dx2 = 123.63f,
                        dy2 = 77.5f,
                        dx3 = 124.0f,
                        dy3 = 80.5f,
                    )
                    // c 0 0 -79.82 25.83 -96.82 40.92
                    curveToRelative(
                        dx1 = 0.0f,
                        dy1 = 0.0f,
                        dx2 = -79.82f,
                        dy2 = 25.83f,
                        dx3 = -96.82f,
                        dy3 = 40.92f,
                    )
                }
                // M88 124.6 c-18.27 16.21 -38.15 48.15 -38.15 48.15 -2.72 2.33 -26.25 -155.62 9 -159 34.53 -3.32 79.1 78.7 80.14 80.35 H139 q.02 .05 0 0 c-.59 .26 -33.93 15.35 -51 30.5
                path(
                    fill = SolidColor(theme.primaryContainer),
                ) {
                    // M 88 124.6
                    moveTo(x = 88.0f, y = 124.6f)
                    // c -18.27 16.21 -38.15 48.15 -38.15 48.15
                    curveToRelative(
                        dx1 = -18.27f,
                        dy1 = 16.21f,
                        dx2 = -38.15f,
                        dy2 = 48.15f,
                        dx3 = -38.15f,
                        dy3 = 48.15f,
                    )
                    // c -2.72 2.33 -26.25 -155.62 9 -159
                    curveToRelative(
                        dx1 = -2.72f,
                        dy1 = 2.33f,
                        dx2 = -26.25f,
                        dy2 = -155.62f,
                        dx3 = 9.0f,
                        dy3 = -159.0f,
                    )
                    // c 34.53 -3.32 79.1 78.7 80.14 80.35
                    curveToRelative(
                        dx1 = 34.53f,
                        dy1 = -3.32f,
                        dx2 = 79.1f,
                        dy2 = 78.7f,
                        dx3 = 80.14f,
                        dy3 = 80.35f,
                    )
                    // H 139
                    horizontalLineTo(x = 139.0f)
                    // q 0.02 0.05 0 0
                    quadToRelative(
                        dx1 = 0.02f,
                        dy1 = 0.05f,
                        dx2 = 0.0f,
                        dy2 = 0.0f,
                    )
                    // c -0.59 0.26 -33.93 15.35 -51 30.5
                    curveToRelative(
                        dx1 = -0.59f,
                        dy1 = 0.26f,
                        dx2 = -33.93f,
                        dy2 = 15.35f,
                        dx3 = -51.0f,
                        dy3 = 30.5f,
                    )
                }
                // M246 180.1 c27.77 -34.92 57.5 -100.5 57.5 -100.5 s-26.24 -5.57 -58.5 -6 c-21.64 -.3 -46.3 2.76 -65 7.5 -35.74 9.04 -62.06 16.54 -90.5 40 -28.02 23.1 -38.46 41.5 -52.5 75 -9.85 23.5 -15.12 39.53 -16 65 -.76 21.85 10 54 10 54 s153.5 -57.67 215 -135
                path(
                    fill = SolidColor(theme.primaryContainer),
                ) {
                    // M 246 180.1
                    moveTo(x = 246.0f, y = 180.1f)
                    // c 27.77 -34.92 57.5 -100.5 57.5 -100.5
                    curveToRelative(
                        dx1 = 27.77f,
                        dy1 = -34.92f,
                        dx2 = 57.5f,
                        dy2 = -100.5f,
                        dx3 = 57.5f,
                        dy3 = -100.5f,
                    )
                    // s -26.24 -5.57 -58.5 -6
                    reflectiveCurveToRelative(
                        dx1 = -26.24f,
                        dy1 = -5.57f,
                        dx2 = -58.5f,
                        dy2 = -6.0f,
                    )
                    // c -21.64 -0.3 -46.3 2.76 -65 7.5
                    curveToRelative(
                        dx1 = -21.64f,
                        dy1 = -0.3f,
                        dx2 = -46.3f,
                        dy2 = 2.76f,
                        dx3 = -65.0f,
                        dy3 = 7.5f,
                    )
                    // c -35.74 9.04 -62.06 16.54 -90.5 40
                    curveToRelative(
                        dx1 = -35.74f,
                        dy1 = 9.04f,
                        dx2 = -62.06f,
                        dy2 = 16.54f,
                        dx3 = -90.5f,
                        dy3 = 40.0f,
                    )
                    // c -28.02 23.1 -38.46 41.5 -52.5 75
                    curveToRelative(
                        dx1 = -28.02f,
                        dy1 = 23.1f,
                        dx2 = -38.46f,
                        dy2 = 41.5f,
                        dx3 = -52.5f,
                        dy3 = 75.0f,
                    )
                    // c -9.85 23.5 -15.12 39.53 -16 65
                    curveToRelative(
                        dx1 = -9.85f,
                        dy1 = 23.5f,
                        dx2 = -15.12f,
                        dy2 = 39.53f,
                        dx3 = -16.0f,
                        dy3 = 65.0f,
                    )
                    // c -0.76 21.85 10 54 10 54
                    curveToRelative(
                        dx1 = -0.76f,
                        dy1 = 21.85f,
                        dx2 = 10.0f,
                        dy2 = 54.0f,
                        dx3 = 10.0f,
                        dy3 = 54.0f,
                    )
                    // s 153.5 -57.67 215 -135
                    reflectiveCurveToRelative(
                        dx1 = 153.5f,
                        dy1 = -57.67f,
                        dx2 = 215.0f,
                        dy2 = -135.0f,
                    )
                }
                // M139 247.1 c.4 -13.17 8.33 -26.5 21.5 -26 13.16 .49 20.13 14.34 19.5 27.5 -.63 13.13 -8.87 26.31 -22 25.5 -12.87 -.8 -19.4 -14.12 -19 -27 m167 1.5 c-.14 -13.18 8.34 -26.63 21.5 -26 12.68 .6 19.85 13.3 19.5 26 -.36 12.87 -8.64 25.61 -21.5 25 -12.37 -.6 -19.37 -12.62 -19.5 -25
                path(
                    fill = SolidColor(theme.secondary),
                ) {
                    // M 139 247.1
                    moveTo(x = 139.0f, y = 247.1f)
                    // c 0.4 -13.17 8.33 -26.5 21.5 -26
                    curveToRelative(
                        dx1 = 0.4f,
                        dy1 = -13.17f,
                        dx2 = 8.33f,
                        dy2 = -26.5f,
                        dx3 = 21.5f,
                        dy3 = -26.0f,
                    )
                    // c 13.16 0.49 20.13 14.34 19.5 27.5
                    curveToRelative(
                        dx1 = 13.16f,
                        dy1 = 0.49f,
                        dx2 = 20.13f,
                        dy2 = 14.34f,
                        dx3 = 19.5f,
                        dy3 = 27.5f,
                    )
                    // c -0.63 13.13 -8.87 26.31 -22 25.5
                    curveToRelative(
                        dx1 = -0.63f,
                        dy1 = 13.13f,
                        dx2 = -8.87f,
                        dy2 = 26.31f,
                        dx3 = -22.0f,
                        dy3 = 25.5f,
                    )
                    // c -12.87 -0.8 -19.4 -14.12 -19 -27
                    curveToRelative(
                        dx1 = -12.87f,
                        dy1 = -0.8f,
                        dx2 = -19.4f,
                        dy2 = -14.12f,
                        dx3 = -19.0f,
                        dy3 = -27.0f,
                    )
                    // m 167 1.5
                    moveToRelative(dx = 167.0f, dy = 1.5f)
                    // c -0.14 -13.18 8.34 -26.63 21.5 -26
                    curveToRelative(
                        dx1 = -0.14f,
                        dy1 = -13.18f,
                        dx2 = 8.34f,
                        dy2 = -26.63f,
                        dx3 = 21.5f,
                        dy3 = -26.0f,
                    )
                    // c 12.68 0.6 19.85 13.3 19.5 26
                    curveToRelative(
                        dx1 = 12.68f,
                        dy1 = 0.6f,
                        dx2 = 19.85f,
                        dy2 = 13.3f,
                        dx3 = 19.5f,
                        dy3 = 26.0f,
                    )
                    // c -0.36 12.87 -8.64 25.61 -21.5 25
                    curveToRelative(
                        dx1 = -0.36f,
                        dy1 = 12.87f,
                        dx2 = -8.64f,
                        dy2 = 25.61f,
                        dx3 = -21.5f,
                        dy3 = 25.0f,
                    )
                    // c -12.37 -0.6 -19.37 -12.62 -19.5 -25
                    curveToRelative(
                        dx1 = -12.37f,
                        dy1 = -0.6f,
                        dx2 = -19.37f,
                        dy2 = -12.62f,
                        dx3 = -19.5f,
                        dy3 = -25.0f,
                    )
                }
                // M202 316.1 c5.5 6 10.4 9.93 19 11 8.85 1.09 18.24 -10.4 18 -11.5 l.5 -10 c-3.5 -1.5 -19.49 -10.7 -19.5 -22.5 -.01 -10.6 20.4 -11.7 24 -11.5 10.5 1.18 21.51 1.36 24.5 11.5 3.36 11.41 -20 21 -20 21 v11.5 c2.56 8.63 11.07 10.28 20 11.5 10.76 1.46 21.1 -19.27 25.5 -11 3.1 5.8 -1.2 10 -6 14.5 -7.47 6.99 -24.5 6 -26 6 -.72 0 -6.37 -.5 -10.5 -3 -4.5 -2.74 -7.5 -7.5 -7.5 -7.5 s-7.3 7.7 -13.5 9.5 c-2.84 .82 -4.55 .88 -7.5 1 -4.12 .15 -6.52 .06 -10.5 -1 -4.39 -1.18 -6.93 -2.2 -10.5 -5 -3.04 -2.4 -5.34 -3.8 -6.5 -7.5 -.88 -2.8 -2.21 -5.59 0 -7.5 1.92 -1.67 6.5 .5 6.5 .5
                path(
                    fill = SolidColor(theme.tertiary),
                ) {
                    // M 202 316.1
                    moveTo(x = 202.0f, y = 316.1f)
                    // c 5.5 6 10.4 9.93 19 11
                    curveToRelative(
                        dx1 = 5.5f,
                        dy1 = 6.0f,
                        dx2 = 10.4f,
                        dy2 = 9.93f,
                        dx3 = 19.0f,
                        dy3 = 11.0f,
                    )
                    // c 8.85 1.09 18.24 -10.4 18 -11.5
                    curveToRelative(
                        dx1 = 8.85f,
                        dy1 = 1.09f,
                        dx2 = 18.24f,
                        dy2 = -10.4f,
                        dx3 = 18.0f,
                        dy3 = -11.5f,
                    )
                    // l 0.5 -10
                    lineToRelative(dx = 0.5f, dy = -10.0f)
                    // c -3.5 -1.5 -19.49 -10.7 -19.5 -22.5
                    curveToRelative(
                        dx1 = -3.5f,
                        dy1 = -1.5f,
                        dx2 = -19.49f,
                        dy2 = -10.7f,
                        dx3 = -19.5f,
                        dy3 = -22.5f,
                    )
                    // c -0.01 -10.6 20.4 -11.7 24 -11.5
                    curveToRelative(
                        dx1 = -0.01f,
                        dy1 = -10.6f,
                        dx2 = 20.4f,
                        dy2 = -11.7f,
                        dx3 = 24.0f,
                        dy3 = -11.5f,
                    )
                    // c 10.5 1.18 21.51 1.36 24.5 11.5
                    curveToRelative(
                        dx1 = 10.5f,
                        dy1 = 1.18f,
                        dx2 = 21.51f,
                        dy2 = 1.36f,
                        dx3 = 24.5f,
                        dy3 = 11.5f,
                    )
                    // c 3.36 11.41 -20 21 -20 21
                    curveToRelative(
                        dx1 = 3.36f,
                        dy1 = 11.41f,
                        dx2 = -20.0f,
                        dy2 = 21.0f,
                        dx3 = -20.0f,
                        dy3 = 21.0f,
                    )
                    // v 11.5
                    verticalLineToRelative(dy = 11.5f)
                    // c 2.56 8.63 11.07 10.28 20 11.5
                    curveToRelative(
                        dx1 = 2.56f,
                        dy1 = 8.63f,
                        dx2 = 11.07f,
                        dy2 = 10.28f,
                        dx3 = 20.0f,
                        dy3 = 11.5f,
                    )
                    // c 10.76 1.46 21.1 -19.27 25.5 -11
                    curveToRelative(
                        dx1 = 10.76f,
                        dy1 = 1.46f,
                        dx2 = 21.1f,
                        dy2 = -19.27f,
                        dx3 = 25.5f,
                        dy3 = -11.0f,
                    )
                    // c 3.1 5.8 -1.2 10 -6 14.5
                    curveToRelative(
                        dx1 = 3.1f,
                        dy1 = 5.8f,
                        dx2 = -1.2f,
                        dy2 = 10.0f,
                        dx3 = -6.0f,
                        dy3 = 14.5f,
                    )
                    // c -7.47 6.99 -24.5 6 -26 6
                    curveToRelative(
                        dx1 = -7.47f,
                        dy1 = 6.99f,
                        dx2 = -24.5f,
                        dy2 = 6.0f,
                        dx3 = -26.0f,
                        dy3 = 6.0f,
                    )
                    // c -0.72 0 -6.37 -0.5 -10.5 -3
                    curveToRelative(
                        dx1 = -0.72f,
                        dy1 = 0.0f,
                        dx2 = -6.37f,
                        dy2 = -0.5f,
                        dx3 = -10.5f,
                        dy3 = -3.0f,
                    )
                    // c -4.5 -2.74 -7.5 -7.5 -7.5 -7.5
                    curveToRelative(
                        dx1 = -4.5f,
                        dy1 = -2.74f,
                        dx2 = -7.5f,
                        dy2 = -7.5f,
                        dx3 = -7.5f,
                        dy3 = -7.5f,
                    )
                    // s -7.3 7.7 -13.5 9.5
                    reflectiveCurveToRelative(
                        dx1 = -7.3f,
                        dy1 = 7.7f,
                        dx2 = -13.5f,
                        dy2 = 9.5f,
                    )
                    // c -2.84 0.82 -4.55 0.88 -7.5 1
                    curveToRelative(
                        dx1 = -2.84f,
                        dy1 = 0.82f,
                        dx2 = -4.55f,
                        dy2 = 0.88f,
                        dx3 = -7.5f,
                        dy3 = 1.0f,
                    )
                    // c -4.12 0.15 -6.52 0.06 -10.5 -1
                    curveToRelative(
                        dx1 = -4.12f,
                        dy1 = 0.15f,
                        dx2 = -6.52f,
                        dy2 = 0.06f,
                        dx3 = -10.5f,
                        dy3 = -1.0f,
                    )
                    // c -4.39 -1.18 -6.93 -2.2 -10.5 -5
                    curveToRelative(
                        dx1 = -4.39f,
                        dy1 = -1.18f,
                        dx2 = -6.93f,
                        dy2 = -2.2f,
                        dx3 = -10.5f,
                        dy3 = -5.0f,
                    )
                    // c -3.04 -2.4 -5.34 -3.8 -6.5 -7.5
                    curveToRelative(
                        dx1 = -3.04f,
                        dy1 = -2.4f,
                        dx2 = -5.34f,
                        dy2 = -3.8f,
                        dx3 = -6.5f,
                        dy3 = -7.5f,
                    )
                    // c -0.88 -2.8 -2.21 -5.59 0 -7.5
                    curveToRelative(
                        dx1 = -0.88f,
                        dy1 = -2.8f,
                        dx2 = -2.21f,
                        dy2 = -5.59f,
                        dx3 = 0.0f,
                        dy3 = -7.5f,
                    )
                    // c 1.92 -1.67 6.5 0.5 6.5 0.5
                    curveToRelative(
                        dx1 = 1.92f,
                        dy1 = -1.67f,
                        dx2 = 6.5f,
                        dy2 = 0.5f,
                        dx3 = 6.5f,
                        dy3 = 0.5f,
                    )
                }
                // M0 289.6 s24 -1.5 41 -1 c9.2 .26 14 0 23.5 1 7.96 .83 25 4.5 25 4.5 m-79 45.5 s62 -19.5 79 -19 m297 -29 s19 -4.5 36 -4 c10 .29 20.5 -.5 30 .5 8 .84 27.5 2.5 27.5 2.5 m-92 29.5 c29.36 4.7 50.5 9.5 78.5 19.5
                path(
                    stroke = SolidColor(theme.tertiary),
                    strokeLineCap = StrokeCap.Round,
                    strokeLineWidth = 10.0f,
                ) {
                    // M 0 289.6
                    moveTo(x = 0.0f, y = 289.6f)
                    // s 24 -1.5 41 -1
                    reflectiveCurveToRelative(
                        dx1 = 24.0f,
                        dy1 = -1.5f,
                        dx2 = 41.0f,
                        dy2 = -1.0f,
                    )
                    // c 9.2 0.26 14 0 23.5 1
                    curveToRelative(
                        dx1 = 9.2f,
                        dy1 = 0.26f,
                        dx2 = 14.0f,
                        dy2 = 0.0f,
                        dx3 = 23.5f,
                        dy3 = 1.0f,
                    )
                    // c 7.96 0.83 25 4.5 25 4.5
                    curveToRelative(
                        dx1 = 7.96f,
                        dy1 = 0.83f,
                        dx2 = 25.0f,
                        dy2 = 4.5f,
                        dx3 = 25.0f,
                        dy3 = 4.5f,
                    )
                    // m -79 45.5
                    moveToRelative(dx = -79.0f, dy = 45.5f)
                    // s 62 -19.5 79 -19
                    reflectiveCurveToRelative(
                        dx1 = 62.0f,
                        dy1 = -19.5f,
                        dx2 = 79.0f,
                        dy2 = -19.0f,
                    )
                    // m 297 -29
                    moveToRelative(dx = 297.0f, dy = -29.0f)
                    // s 19 -4.5 36 -4
                    reflectiveCurveToRelative(
                        dx1 = 19.0f,
                        dy1 = -4.5f,
                        dx2 = 36.0f,
                        dy2 = -4.0f,
                    )
                    // c 10 0.29 20.5 -0.5 30 0.5
                    curveToRelative(
                        dx1 = 10.0f,
                        dy1 = 0.29f,
                        dx2 = 20.5f,
                        dy2 = -0.5f,
                        dx3 = 30.0f,
                        dy3 = 0.5f,
                    )
                    // c 8 0.84 27.5 2.5 27.5 2.5
                    curveToRelative(
                        dx1 = 8.0f,
                        dy1 = 0.84f,
                        dx2 = 27.5f,
                        dy2 = 2.5f,
                        dx3 = 27.5f,
                        dy3 = 2.5f,
                    )
                    // m -92 29.5
                    moveToRelative(dx = -92.0f, dy = 29.5f)
                    // c 29.36 4.7 50.5 9.5 78.5 19.5
                    curveToRelative(
                        dx1 = 29.36f,
                        dy1 = 4.7f,
                        dx2 = 50.5f,
                        dy2 = 9.5f,
                        dx3 = 78.5f,
                        dy3 = 19.5f,
                    )
                }
                // M111.5 769.6 s-4.53 10.08 -5.5 16.5 c-1.27 8.38 3.3 20 3.3 20 m131.21 -31 s-2.32 5.08 -3.29 11.5 c-1.26 8.38 2.28 20.5 2.28 20.5 m34.06 -31 s-2.32 5.08 -3.29 11.5 c-1.26 8.38 2.73 20 2.73 20 m84.54 -32.5 s-2.32 5.08 -3.3 11.5 c-1.26 8.38 6.26 17 6.26 17 m-214 -29 c-.57 3.76 -2.86 10.56 -3 17.5 -.11 5.69 1.5 14.5 1.5 14.5
                path(
                    stroke = SolidColor(theme.outline),
                    strokeLineCap = StrokeCap.Round,
                    strokeLineWidth = 10.0f,
                ) {
                    // M 111.5 769.6
                    moveTo(x = 111.5f, y = 769.6f)
                    // s -4.53 10.08 -5.5 16.5
                    reflectiveCurveToRelative(
                        dx1 = -4.53f,
                        dy1 = 10.08f,
                        dx2 = -5.5f,
                        dy2 = 16.5f,
                    )
                    // c -1.27 8.38 3.3 20 3.3 20
                    curveToRelative(
                        dx1 = -1.27f,
                        dy1 = 8.38f,
                        dx2 = 3.3f,
                        dy2 = 20.0f,
                        dx3 = 3.3f,
                        dy3 = 20.0f,
                    )
                    // m 131.21 -31
                    moveToRelative(dx = 131.21f, dy = -31.0f)
                    // s -2.32 5.08 -3.29 11.5
                    reflectiveCurveToRelative(
                        dx1 = -2.32f,
                        dy1 = 5.08f,
                        dx2 = -3.29f,
                        dy2 = 11.5f,
                    )
                    // c -1.26 8.38 2.28 20.5 2.28 20.5
                    curveToRelative(
                        dx1 = -1.26f,
                        dy1 = 8.38f,
                        dx2 = 2.28f,
                        dy2 = 20.5f,
                        dx3 = 2.28f,
                        dy3 = 20.5f,
                    )
                    // m 34.06 -31
                    moveToRelative(dx = 34.06f, dy = -31.0f)
                    // s -2.32 5.08 -3.29 11.5
                    reflectiveCurveToRelative(
                        dx1 = -2.32f,
                        dy1 = 5.08f,
                        dx2 = -3.29f,
                        dy2 = 11.5f,
                    )
                    // c -1.26 8.38 2.73 20 2.73 20
                    curveToRelative(
                        dx1 = -1.26f,
                        dy1 = 8.38f,
                        dx2 = 2.73f,
                        dy2 = 20.0f,
                        dx3 = 2.73f,
                        dy3 = 20.0f,
                    )
                    // m 84.54 -32.5
                    moveToRelative(dx = 84.54f, dy = -32.5f)
                    // s -2.32 5.08 -3.3 11.5
                    reflectiveCurveToRelative(
                        dx1 = -2.32f,
                        dy1 = 5.08f,
                        dx2 = -3.3f,
                        dy2 = 11.5f,
                    )
                    // c -1.26 8.38 6.26 17 6.26 17
                    curveToRelative(
                        dx1 = -1.26f,
                        dy1 = 8.38f,
                        dx2 = 6.26f,
                        dy2 = 17.0f,
                        dx3 = 6.26f,
                        dy3 = 17.0f,
                    )
                    // m -214 -29
                    moveToRelative(dx = -214.0f, dy = -29.0f)
                    // c -0.57 3.76 -2.86 10.56 -3 17.5
                    curveToRelative(
                        dx1 = -0.57f,
                        dy1 = 3.76f,
                        dx2 = -2.86f,
                        dy2 = 10.56f,
                        dx3 = -3.0f,
                        dy3 = 17.5f,
                    )
                    // c -0.11 5.69 1.5 14.5 1.5 14.5
                    curveToRelative(
                        dx1 = -0.11f,
                        dy1 = 5.69f,
                        dx2 = 1.5f,
                        dy2 = 14.5f,
                        dx3 = 1.5f,
                        dy3 = 14.5f,
                    )
                }
            }
        }.build().also { _cat = Pair(theme.primary.hashCode(), it) }
    }

@Preview
@Composable
private fun IconPreview() {
    SimpleScannerTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = Cat,
                contentDescription = null,
                modifier = Modifier
                    .width((636.0).dp)
                    .height((831.0).dp),
            )
        }
    }
}

@Suppress("ObjectPropertyName")
private var _cat: Pair<Int, ImageVector>? = null
