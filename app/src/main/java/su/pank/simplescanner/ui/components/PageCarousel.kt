package su.pank.simplescanner.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import su.pank.simplescanner.R
import su.pank.simplescanner.ui.theme.SimpleScannerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageCarousel(pages: List<ImageBitmap>, modifier: Modifier = Modifier) {
    val state = rememberCarouselState { pages.size }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalCenteredHeroCarousel(state, modifier, contentPadding = PaddingValues(horizontal = 10.dp), itemSpacing = 8.dp) {
            val page = pages[it]
            Image(
                page, contentDescription = "page", modifier = Modifier

                    .fillMaxSize()
                    .maskClip(MaterialTheme.shapes.extraLarge),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    RoundedCornerShape(100.dp)
                )
                .padding(10.dp)
        ) {
            if (pages.size > 10) {
                Text("${state.currentItem + 1}/${pages.size}", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
            } else {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    repeat(pages.size) {
                        val color by animateColorAsState(if (state.currentItem == it) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary)
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .background(
                                    color,
                                    CircleShape
                                )
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun PageCarouselPreview() {
    val photo = ImageBitmap.imageResource(id = R.drawable.photo)
    SimpleScannerTheme {
        PageCarousel(
            listOf(photo, photo, photo, photo, photo, photo, photo, photo, photo, photo, photo, photo, photo),
            modifier = Modifier.aspectRatio(230f / 300f)
        )
    }
}

