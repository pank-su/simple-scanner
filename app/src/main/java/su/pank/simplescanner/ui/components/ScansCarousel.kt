package su.pank.simplescanner.ui.components

import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateDp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import su.pank.simplescanner.R
import su.pank.simplescanner.data.models.Scan
import su.pank.simplescanner.data.models.testScan
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import su.pank.simplescanner.utils.DarkLightPreview
import su.pank.simplescanner.utils.LocalNavAnimatedVisibilityScope
import su.pank.simplescanner.utils.LocalSharedTransitionScope
import su.pank.simplescanner.utils.LocalePreview
import su.pank.simplescanner.utils.SharedElementScopeCompositionLocal
import su.pank.simplescanner.utils.currentOrThrow
import su.pank.simplescanner.utils.rememberTimeFormatter
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun ScansCarousel(
    state: ScansUiState,
    onClickedScan: (Scan) -> Unit,
    modifier: Modifier = Modifier
) {


    Box(
        modifier
            .height(230.dp)
            .widthIn(min = 300.dp)
    ) {

        when (state) {
            is ScansUiState.Success -> {
                SuccessState(state.scans, state.timeNow, onClickedScan)
            }

            ScansUiState.Empty, ScansUiState.Error -> EmptyState()
            ScansUiState.Loading -> LoadingState()
        }


    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun LoadingState() {
    ElevatedCard(modifier = Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LoadingIndicator()
        }
    }
}

@Composable
internal fun EmptyState() {
    ElevatedCard(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically)
        ) {
            Icon(
                painterResource(R.drawable.inbox),
                contentDescription = "empty scans",
                modifier = Modifier.size(48.dp)
            )
            Text(
                stringResource(R.string.empty_scans),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@OptIn(
    ExperimentalTime::class, ExperimentalMaterial3Api::class,
    ExperimentalSharedTransitionApi::class, ExperimentalUuidApi::class
)
@Composable
private fun SuccessState(
    scans: List<Scan>,
    timeNow: Instant,
    onClickedScan: (Scan) -> Unit
) {
    val timeFormatter = rememberTimeFormatter()
    val context = LocalContext.current
    val state = rememberCarouselState { scans.size.coerceAtLeast(3) }


    HorizontalMultiBrowseCarousel(
        state,
        preferredItemWidth = 145.dp,
        itemSpacing = 10.dp,
        modifier = Modifier.fillMaxSize(),
        userScrollEnabled = scans.size >= 3,
    ) {
        val item = scans.getOrNull(it)
        if (item == null) {
            Spacer(Modifier.fillMaxSize())
            return@HorizontalMultiBrowseCarousel
        }
        val preview = remember(item) {
            item.imageRequests(context).firstOrNull()?.build()
        }
        val key = remember(item) {
            item.id.toHexString()
        }
        val timeText by remember(timeNow) {
            derivedStateOf {
                timeFormatter.formatDuration(timeNow - item.savedAt)
            }
        }



        ElevatedCard(
            onClick = {

                onClickedScan(item)
            },
            modifier = Modifier
                .fillMaxSize()
                .maskClip(MaterialTheme.shapes.medium)
        ) {

            Column(
                Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {

                val sharedTransitionScope = LocalSharedTransitionScope.currentOrThrow
                val animatedContentScope = LocalNavAnimatedVisibilityScope.currentOrThrow
                val rounderCornerAnim by animatedContentScope.transition.animateDp(label = "rounded corners") { enterExitState ->
                    when (enterExitState) {
                        EnterExitState.PreEnter -> 28.dp
                        EnterExitState.Visible -> 4.dp
                        EnterExitState.PostExit -> 4.dp
                    }
                }

                with(sharedTransitionScope) {

                    Box(
                        modifier = Modifier
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
                            .maskClip(
                                RoundedCornerShape(4.dp)
                            )
                            .weight(1f)
                    ) {

                        AsyncImage(
                            preview,
                            contentDescription = "preview",
                            modifier = Modifier
                                .sharedElement(
                                    sharedTransitionScope.rememberSharedContentState(
                                        AnimKeys.pageKey(0, key)
                                    ),
                                    animatedVisibilityScope = animatedContentScope,
                                )
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop,

                            )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            item.name,
                            style = MaterialTheme.typography.titleSmall,
                            maxLines = 1,

                            overflow = TextOverflow.MiddleEllipsis
                        )
                        Text(
                            "$timeText ${stringResource(R.string.ago)}",
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1
                        )
                    }

                    when (item) {
                        is Scan.ScanJpg -> Icon(
                            painterResource(R.drawable.jpeg_icon),
                            contentDescription = "File extension"
                        )

                        is Scan.ScanPdf -> Icon(
                            painterResource(R.drawable.pdf_icon),
                            contentDescription = "File extension"
                        )

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@LocalePreview
@DarkLightPreview
@Preview
@Composable
fun ScansCarouselPreview() {
    val scans = listOf<Scan>(
        testScan,
        testScan,
        testScan,
        testScan
    )
    SimpleScannerTheme {
        SharedElementScopeCompositionLocal {
            ScansCarousel(
                ScansUiState.Success(
                    scans = scans,
                    timeNow = Clock.System.now(),
                ),
                {}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@LocalePreview
@DarkLightPreview
@Composable
fun EmptyScansCarouselPreview() {

    SimpleScannerTheme {
        ScansCarousel(
            ScansUiState.Empty,
            {}
        )
    }
}

@Composable
@DarkLightPreview
fun LoadingScansCarouselPreview() {
    SimpleScannerTheme {
        SharedElementScopeCompositionLocal {

            ScansCarousel(ScansUiState.Loading, {})
        }
    }
}

@OptIn(ExperimentalTime::class)
sealed interface ScansUiState {
    object Loading : ScansUiState
    object Error : ScansUiState

    object Empty : ScansUiState

    data class Success(val scans: List<Scan>, val timeNow: Instant) : ScansUiState {
        init {
            require(scans.isNotEmpty())
        }
    }
}
