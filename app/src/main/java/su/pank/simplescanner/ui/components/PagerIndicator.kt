package su.pank.simplescanner.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.CarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import su.pank.simplescanner.ui.theme.SimpleScannerTheme


/**
 * Need todo features
 *
 * * Long press to scroll to page
 * * Click to select page
 * * Animation when scroll (wait fix carousel)
 */

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PageIndicator(size: Int, state: CarouselState, modifier: Modifier = Modifier) {


    Box(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                RoundedCornerShape(100.dp)
            )
            .padding(10.dp)
    ) {
        if (size > 10) {
            Text(
                "${state.currentItem + 1}/${size}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        } else {


            Row {
                repeat(size) {
                    val isForwardItem = (it == state.currentItem + 1)
                    val isBackwardItem = (it == state.currentItem - 1)
                    val color by animateColorAsState(if (state.currentItem == it || state.isScrollInProgress && (state.lastScrolledForward && isForwardItem || state.lastScrolledBackward && isBackwardItem)) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary)
                    // TODO: wait fix
                    val addWidth by animateDpAsState(if ((state.lastScrolledForward && isBackwardItem || state.lastScrolledBackward && isForwardItem)) 10.dp else 0.dp)



                    Box(
                        modifier = Modifier
                            .size(6.dp + addWidth, 6.dp)
                            .background(
                                color,
                                CircleShape
                            )
                    )
                    if (it != size - 1) {
                        Box(Modifier.size(10.dp - addWidth, 6.dp))
                    }
                    state
                }
            }
        }
    }
}

@PreviewLightDark
@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PageIndicatorPreview() {
    val state = CarouselState { 5 }
    SimpleScannerTheme {
        PageIndicator(size = 5, state = state)
    }
}

@PreviewLightDark
@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PageIndicatorLargePreview() {
    val state = CarouselState { 42 }
    SimpleScannerTheme {
        PageIndicator(size = 42, state = state)
    }
}