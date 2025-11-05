package su.pank.simplescanner.ui.components

import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import su.pank.simplescanner.R
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import su.pank.simplescanner.utils.LocalSharedTransitionScope
import su.pank.simplescanner.utils.SharedElementScopeCompositionLocal
import su.pank.simplescanner.utils.currentOrThrow

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class,
    ExperimentalSharedTransitionApi::class
)
@Composable
fun PageCarousel(
    pages: List<ImageRequest>,
    key: String,
    modifier: Modifier = Modifier,
    withPager: Boolean = true,
) {
    val size by remember {
        derivedStateOf { pages.size }
    }
    val sharedTransitionScope = LocalSharedTransitionScope.currentOrThrow
    val animatedContentScope = LocalNavAnimatedContentScope.current

    val rounderCornerAnim by animatedContentScope.transition.animateDp(label = "rounded corners") { enterExitState ->
        when (enterExitState) {
            EnterExitState.PreEnter -> 4.dp
            EnterExitState.Visible -> 28.dp
            EnterExitState.PostExit -> 28.dp
        }
    }
    val state = rememberCarouselState { size }
    if (size == 1) {
        with(sharedTransitionScope) {

            Box(
                modifier = modifier
                    .sharedBounds(
                        sharedTransitionScope.rememberSharedContentState(
                            AnimKeys.containerKey(
                                0,
                                key
                            )
                        ),
                        animatedContentScope,
                        clipInOverlayDuringTransition = OverlayClip(
                            RoundedCornerShape(
                                rounderCornerAnim
                            )
                        ),
                        resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                    )
                    .clip(RoundedCornerShape(28.dp))
                    .fillMaxSize()
            ) {
                AsyncImage(
                    pages[0],
                    contentDescription = "page",
                    modifier = Modifier
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(
                                key = AnimKeys.pageKey(
                                    0,
                                    key
                                )
                            ),
                            animatedContentScope,
                        )
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
        return
    }
    with(sharedTransitionScope) {

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
                        .sharedBounds(
                            sharedTransitionScope.rememberSharedContentState(
                                AnimKeys.containerKey(
                                    it,
                                    key
                                )
                            ),
                            animatedContentScope,
                            clipInOverlayDuringTransition = OverlayClip(
                                RoundedCornerShape(
                                    rounderCornerAnim
                                )
                            ),

                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds

                        )
                        .maskClip(RoundedCornerShape(28.dp))
                        .fillMaxSize()
                ) {
                    AsyncImage(
                        page,
                        contentDescription = "page",
                        modifier = Modifier
                            .sharedElement(
                                sharedTransitionScope.rememberSharedContentState(
                                    key = AnimKeys.pageKey(
                                        it,
                                        key
                                    )
                                ),
                                animatedContentScope,
                            )
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

            }

            //Spacer(Modifier.height(8.dp))
            with(animatedContentScope) {
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
        }

    }

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
                "test",
                modifier = Modifier.aspectRatio(230f / 300f)
            )
        }
    }
}
