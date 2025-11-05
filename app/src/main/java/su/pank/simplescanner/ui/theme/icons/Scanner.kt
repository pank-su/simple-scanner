package su.pank.simplescanner.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import su.pank.simplescanner.ui.theme.SimpleScannerTheme

val Scanner: ImageVector
    get() {
        val current = _scanner
        if (current != null) return current

        return ImageVector.Builder(
            name = "your.app.package.theme.YourAppComposeTheme.Scanner",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            // M2 6 V1 h5 v2 H4 v3z m18 0 V3 h-3 V1 h5 v5z M2 23 v-5 h2 v3 h3 v2z m15 0 v-2 h3 v-3 h2 v5z M7 18 h10 V6 H7z m0 2 a2 2 0 0 1 -1.41 -.59 A2 2 0 0 1 5 18 V6 q0 -.82 .59 -1.41 A2 2 0 0 1 7 4 h10 q.83 0 1.41 .59 A2 2 0 0 1 19 6 v12 q0 .83 -.59 1.41 A2 2 0 0 1 17 20z m2 -10 h6 V8 H9z m0 3 h6 v-2 H9z m0 3 h6 v-2 H9z
            path(
                fill = SolidColor(Color(0xFF1F1F1F)),
            ) {
                // M 2 6
                moveTo(x = 2.0f, y = 6.0f)
                // V 1
                verticalLineTo(y = 1.0f)
                // h 5
                horizontalLineToRelative(dx = 5.0f)
                // v 2
                verticalLineToRelative(dy = 2.0f)
                // H 4
                horizontalLineTo(x = 4.0f)
                // v 3z
                verticalLineToRelative(dy = 3.0f)
                close()
                // m 18 0
                moveToRelative(dx = 18.0f, dy = 0.0f)
                // V 3
                verticalLineTo(y = 3.0f)
                // h -3
                horizontalLineToRelative(dx = -3.0f)
                // V 1
                verticalLineTo(y = 1.0f)
                // h 5
                horizontalLineToRelative(dx = 5.0f)
                // v 5z
                verticalLineToRelative(dy = 5.0f)
                close()
                // M 2 23
                moveTo(x = 2.0f, y = 23.0f)
                // v -5
                verticalLineToRelative(dy = -5.0f)
                // h 2
                horizontalLineToRelative(dx = 2.0f)
                // v 3
                verticalLineToRelative(dy = 3.0f)
                // h 3
                horizontalLineToRelative(dx = 3.0f)
                // v 2z
                verticalLineToRelative(dy = 2.0f)
                close()
                // m 15 0
                moveToRelative(dx = 15.0f, dy = 0.0f)
                // v -2
                verticalLineToRelative(dy = -2.0f)
                // h 3
                horizontalLineToRelative(dx = 3.0f)
                // v -3
                verticalLineToRelative(dy = -3.0f)
                // h 2
                horizontalLineToRelative(dx = 2.0f)
                // v 5z
                verticalLineToRelative(dy = 5.0f)
                close()
                // M 7 18
                moveTo(x = 7.0f, y = 18.0f)
                // h 10
                horizontalLineToRelative(dx = 10.0f)
                // V 6
                verticalLineTo(y = 6.0f)
                // H 7z
                horizontalLineTo(x = 7.0f)
                close()
                // m 0 2
                moveToRelative(dx = 0.0f, dy = 2.0f)
                // a 2 2 0 0 1 -1.41 -0.59
                arcToRelative(
                    a = 2.0f,
                    b = 2.0f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    dx1 = -1.41f,
                    dy1 = -0.59f,
                )
                // A 2 2 0 0 1 5 18
                arcTo(
                    horizontalEllipseRadius = 2.0f,
                    verticalEllipseRadius = 2.0f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    x1 = 5.0f,
                    y1 = 18.0f,
                )
                // V 6
                verticalLineTo(y = 6.0f)
                // q 0 -0.82 0.59 -1.41
                quadToRelative(
                    dx1 = 0.0f,
                    dy1 = -0.82f,
                    dx2 = 0.59f,
                    dy2 = -1.41f,
                )
                // A 2 2 0 0 1 7 4
                arcTo(
                    horizontalEllipseRadius = 2.0f,
                    verticalEllipseRadius = 2.0f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    x1 = 7.0f,
                    y1 = 4.0f,
                )
                // h 10
                horizontalLineToRelative(dx = 10.0f)
                // q 0.83 0 1.41 0.59
                quadToRelative(
                    dx1 = 0.83f,
                    dy1 = 0.0f,
                    dx2 = 1.41f,
                    dy2 = 0.59f,
                )
                // A 2 2 0 0 1 19 6
                arcTo(
                    horizontalEllipseRadius = 2.0f,
                    verticalEllipseRadius = 2.0f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    x1 = 19.0f,
                    y1 = 6.0f,
                )
                // v 12
                verticalLineToRelative(dy = 12.0f)
                // q 0 0.83 -0.59 1.41
                quadToRelative(
                    dx1 = 0.0f,
                    dy1 = 0.83f,
                    dx2 = -0.59f,
                    dy2 = 1.41f,
                )
                // A 2 2 0 0 1 17 20z
                arcTo(
                    horizontalEllipseRadius = 2.0f,
                    verticalEllipseRadius = 2.0f,
                    theta = 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = true,
                    x1 = 17.0f,
                    y1 = 20.0f,
                )
                close()
                // m 2 -10
                moveToRelative(dx = 2.0f, dy = -10.0f)
                // h 6
                horizontalLineToRelative(dx = 6.0f)
                // V 8
                verticalLineTo(y = 8.0f)
                // H 9z
                horizontalLineTo(x = 9.0f)
                close()
                // m 0 3
                moveToRelative(dx = 0.0f, dy = 3.0f)
                // h 6
                horizontalLineToRelative(dx = 6.0f)
                // v -2
                verticalLineToRelative(dy = -2.0f)
                // H 9z
                horizontalLineTo(x = 9.0f)
                close()
                // m 0 3
                moveToRelative(dx = 0.0f, dy = 3.0f)
                // h 6
                horizontalLineToRelative(dx = 6.0f)
                // v -2
                verticalLineToRelative(dy = -2.0f)
                // H 9z
                horizontalLineTo(x = 9.0f)
                close()
            }
        }.build().also { _scanner = it }
    }

@Preview
@Composable
private fun IconPreview() {
    SimpleScannerTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.White)
        ) {
            Image(
                imageVector = Scanner,
                contentDescription = null,
                modifier = Modifier
                    .width((24.0).dp)
                    .height((24.0).dp),
            )
        }
    }
}

@Suppress("ObjectPropertyName")
private var _scanner: ImageVector? = null
