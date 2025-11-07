package su.pank.simplescanner.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import net.engawapg.lib.zoomable.ExperimentalZoomableApi
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable
import su.pank.simplescanner.R
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import su.pank.simplescanner.utils.LocalSharedTransitionScope
import su.pank.simplescanner.utils.SharedElementScopeCompositionLocal
import su.pank.simplescanner.utils.currentOrThrow

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class,
    ExperimentalSharedTransitionApi::class, ExperimentalZoomableApi::class
)
@Composable
fun PageCarousel(
    pages: List<ImageRequest>,
    modifier: Modifier = Modifier,
    withPager: Boolean = true,
    zoomable: Boolean = true
) {
    val size by remember {
        derivedStateOf { pages.size }
    }
    val sharedTransitionScope = LocalSharedTransitionScope.currentOrThrow
    val animatedContentScope = LocalNavAnimatedContentScope.current
    var zoomableImage: ImageRequest? by remember { mutableStateOf(null) }


    val state = rememberCarouselState { size }
    SharedTransitionLayout {
        AnimatedContent(zoomableImage) {
            if (it == null) {
                if (size == 1) {
                    Box(
                        modifier = modifier
                            .clip(RoundedCornerShape(28.dp))
                            .fillMaxSize()
                    ) {
                        AsyncImage(
                            pages[0],
                            contentDescription = "page",
                            modifier = Modifier
                                .sharedElement(
                                    rememberSharedContentState("image_0"),
                                    this@AnimatedContent
                                )
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    return@AnimatedContent
                }




                Box(modifier = modifier) {


                    HorizontalCenteredHeroCarousel(
                        state,
                        modifier = Modifier.fillMaxSize(),
                        //contentPadding = PaddingValues(horizontal = 10.dp),
                        itemSpacing = 8.dp
                    ) {
                        val page = pages[it]


                        Box(
                            modifier = Modifier
                                .maskClip(RoundedCornerShape(28.dp))
                                .fillMaxSize()
                        ) {
                            AsyncImage(
                                page,
                                contentDescription = "page",
                                modifier = Modifier
                                    .sharedElement(
                                        sharedTransitionScope.rememberSharedContentState("page"),
                                        animatedContentScope
                                    )
                                    .pointerInput(Unit) {
                                        detectTapGestures(onDoubleTap = {
                                            zoomableImage = page
                                        })
                                    }
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }


                    //Spacer(Modifier.height(8.dp))
                    if (withPager)
                        PageIndicator(
                            size, state, modifier = Modifier
                                .renderInSharedTransitionScopeOverlay(zIndexInOverlay = 1f)
                                .animateEnterExit(
                                    enter = fadeIn() + slideInVertically {
                                        it
                                    },
                                    exit = fadeOut() + slideOutVertically {
                                        it
                                    }
                                )
                                .align(Alignment.BottomCenter)
                                .padding(10.dp)
                        )

                }
            } else {
                SharedElementScopeCompositionLocal {
                    ZoomableImage(
                        page = it,
                        modifier = Modifier
                            .fillMaxSize()
                            .sharedElement(
                                sharedTransitionScope.rememberSharedContentState("page"),
                                animatedContentScope
                            )
                    )
                }
            }

        }
    }
}


@Composable
fun ZoomableImage(page: ImageRequest, modifier: Modifier = Modifier) {
    val zoomState = rememberZoomState()
    AsyncImage(
        page,
        contentDescription = "page",
        modifier = modifier
            .fillMaxSize()
            .zoomable(zoomState),
        contentScale = ContentScale.Fit
    )

}

@Preview
@Composable
private fun PageCarouselPreview() {
    ImageBitmap.imageResource(id = R.drawable.photo)
    SimpleScannerTheme {
        SharedElementScopeCompositionLocal {

            PageCarousel(
                listOf(
                    ImageRequest.Builder(LocalContext.current).data(R.drawable.photo).build(),
                    ImageRequest.Builder(LocalContext.current).data(R.drawable.photo).build(),
                    ImageRequest.Builder(LocalContext.current).data(R.drawable.photo)
                        .build(),
                    ImageRequest.Builder(LocalContext.current).data(R.drawable.photo)
                        .build()


                ),
                modifier = Modifier.aspectRatio(230f / 300f)
            )
        }
    }
}
