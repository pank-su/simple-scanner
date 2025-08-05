package su.pank.simplescanner.ui.splash_error

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.toArgb

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp

import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import kotlinx.serialization.Serializable
import su.pank.simplescanner.R
import su.pank.simplescanner.ui.theme.SimpleScannerTheme

@Serializable
data class Error(val message: String)

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SplashErrorView(message: String, onRestart: () -> Unit) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error_anim))
    val progress by animateLottieCompositionAsState(composition, speed = 2.5f)

    val errorContainer = MaterialTheme.colorScheme.errorContainer
    val error = MaterialTheme.colorScheme.error
    val surface = MaterialTheme.colorScheme.surface
    val surfaceContainer = MaterialTheme.colorScheme.surfaceContainer
    val onSurface = MaterialTheme.colorScheme.onSurface


    val bgColor by remember {
        derivedStateOf {
            lerp(surface, errorContainer, progress)
        }
    }
    val iconColor by remember {
        derivedStateOf {
            lerp(surfaceContainer, errorContainer, progress)
        }
    }

    val contentColor by remember {
        derivedStateOf {
            lerp(onSurface, error, progress)

        }
    }

    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = contentColor.toArgb(),
            keyPath = arrayOf("**")
        )
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        val backgroundScale by remember {
            derivedStateOf {
                lerp(1f, (this.maxWidth / 160.dp), progress)
            }
        }
        val iconSize by remember {
            derivedStateOf {
                lerp(120.dp, 60.dp, progress)
            }
        }




        Box(modifier = Modifier.size(240.dp), contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .background(
                        iconColor,
                        shape = CircleShape
                    )
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier
                        .size(iconSize),
                    dynamicProperties = dynamicProperties
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.height(140.dp * progress)
                ) {

                    Text(
                        stringResource(R.string.error),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.displaySmall,
                        color = contentColor
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        message,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium,
                        color = contentColor
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    AnimatedVisibility(progress > 0.9f) {

                        Button(
                            onClick = onRestart,
                            shapes = ButtonDefaults.shapes(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error,
                                contentColor = MaterialTheme.colorScheme.onError
                            )
                        ) {
                            Text(
                                stringResource(R.string.restart_app),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }

            }
        }
    }
}


@Preview
@Composable
fun SplashErrorViewPreview() {
    SimpleScannerTheme {
        SplashErrorView("Preview message"){}
    }
}

