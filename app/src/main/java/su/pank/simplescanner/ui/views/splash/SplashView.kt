package su.pank.simplescanner.ui.views.splash

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import kotlinx.serialization.Serializable
import su.pank.simplescanner.R
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import su.pank.simplescanner.utils.LocalSharedTransitionScope
import su.pank.simplescanner.utils.SharedElementScopeCompositionLocal
import su.pank.simplescanner.utils.currentOrThrow

@Serializable
data object Splash : NavKey

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SplashView() {
    val sharedTransitionScope = LocalSharedTransitionScope.currentOrThrow
    val animatedContentScope = LocalNavAnimatedContentScope.current

    with(sharedTransitionScope) {
        Box(
            modifier = Modifier
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.size(240.dp), contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .background(MaterialTheme.colorScheme.surfaceContainer, shape = CircleShape)
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState("splash-logo-bg"),
                            animatedContentScope
                        )
                )
                Icon(
                    painterResource(R.drawable.scan),
                    "scan",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .size(120.dp)
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState("splash-logo"),
                            animatedContentScope
                        )
                )

            }
        }


    }

}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
fun SplashViewPreview() {
    SimpleScannerTheme {
        SharedElementScopeCompositionLocal {
            SplashView()
        }

    }
}
