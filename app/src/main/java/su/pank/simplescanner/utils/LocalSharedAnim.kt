package su.pank.simplescanner.utils

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }


// Use only in tests/preview
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedElementScopeCompositionLocal(content: @Composable () -> Unit) {

    val state = remember {
        false
    }
    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this
        ) {
            // This could also be your top-level NavHost as this provides an AnimatedContentScope
            AnimatedContent(state, label = "Top level AnimatedContent") { targetState ->
                CompositionLocalProvider(LocalNavAnimatedVisibilityScope provides this) {
                    content()
                }
                if (targetState) {
                }
            }
        }
    }
}

@Composable
fun AnimatedContentScope.AnimatedScopeProvider(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalNavAnimatedVisibilityScope provides this) {
        content()
    }
}