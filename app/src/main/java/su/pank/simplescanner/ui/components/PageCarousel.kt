package su.pank.simplescanner.ui.components

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import su.pank.simplescanner.R
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import su.pank.simplescanner.utils.LocalNavAnimatedVisibilityScope
import su.pank.simplescanner.utils.LocalSharedTransitionScope
import su.pank.simplescanner.utils.currentOrThrow

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class,
    ExperimentalSharedTransitionApi::class
)
@Composable
fun PageCarousel(pages: List<ImageRequest>, key: String, modifier: Modifier = Modifier) {
    val size by remember {
        derivedStateOf { pages.size }
    }
    val state = rememberCarouselState { size }


    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        HorizontalCenteredHeroCarousel(
            state,
            Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 10.dp),
            itemSpacing = 8.dp
        ) {
            val page = pages[it]
            val sharedTransitionScope = LocalSharedTransitionScope.currentOrThrow
            val animatedContentScope = LocalNavAnimatedVisibilityScope.currentOrThrow
            with(sharedTransitionScope) {
            Box(
                modifier = Modifier.sharedElement(
                    sharedTransitionScope.rememberSharedContentState(key = "$key$it"),
                    animatedContentScope
                )
                    .fillMaxSize()
                    .maskClip(MaterialTheme.shapes.extraLarge),
            ) {


                    AsyncImage(
                        page,
                        contentDescription = "page",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

            }
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
            if (size > 10) {
                Text(
                    "${state.currentItem + 1}/${size}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            } else {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    repeat(size) {
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
            listOf(
                ImageRequest.Builder(LocalContext.current).data(R.drawable.photo).build()


            ),
            "test",
            modifier = Modifier.aspectRatio(230f / 300f)
        )
    }
}

