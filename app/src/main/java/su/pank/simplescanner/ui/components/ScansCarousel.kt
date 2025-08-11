package su.pank.simplescanner.ui.components

import android.graphics.BitmapFactory
import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.pdf.SandboxedPdfLoader
import su.pank.simplescanner.R
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.data.models.TestItem
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import su.pank.simplescanner.utils.DarkLightPreview
import su.pank.simplescanner.utils.LocalePreview
import su.pank.simplescanner.utils.rememberTimeFormatter
import java.io.File
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun ScansCarousel(
    state: ScansUiState,
    onClickedScan: (ScannedItem) -> Unit = {},
    modifier: Modifier = Modifier
) {


    Box(
        modifier
            .height(211.dp)
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
private fun LoadingState() {
    ElevatedCard(modifier = Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LoadingIndicator()
        }
    }
}

@Composable
private fun EmptyState() {
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

@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
@Composable
fun SuccessState(scans: List<ScannedItem>, timeNow: Instant, onClickedScan: (ScannedItem) -> Unit) {
    val timeFormatter = rememberTimeFormatter()
    val context = LocalContext.current

    val pdfLoader = remember {
        SandboxedPdfLoader(context)
    }


    val state = rememberCarouselState { scans.size }


    HorizontalMultiBrowseCarousel(
        state,
        preferredItemWidth = 145.dp,
        itemSpacing = 10.dp,
        modifier = Modifier.fillMaxSize()
    ) {
        val item = scans.getOrNull(it)
        if (item == null) {
            Spacer(Modifier.fillMaxSize())
            return@HorizontalMultiBrowseCarousel
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
            var preview: ImageBitmap? by remember {
                mutableStateOf(
                    null
                )
            }

            LaunchedEffect(item) {
                when (item) {
                    is ScannedItem.JpgItem -> {
                        val files = item.files
                        val file = files.firstOrNull()
                        if (file == null) {
                            preview = null
                        }
                        preview = BitmapFactory.decodeFile(files.first())
                            .asImageBitmap()
                    }

                    is ScannedItem.PdfFile -> {

                        preview =
                            pdfLoader.openDocument(File(item.file).toUri()).getPageBitmapSource(0)
                                .getBitmap(
                                    Size(1000, 1000)
                                ).asImageBitmap()

                    }

                    is TestItem -> {
                        preview = BitmapFactory.decodeResource(context.resources, R.drawable.photo)
                            .asImageBitmap()
                    }
                }
            }

            Column(
                Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(
                            RoundedCornerShape(4.dp)
                        )
                ) {
                    if (preview != null) {
                        Image(
                            preview!!,
                            contentDescription = "pdf image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.surfaceContainer)
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(item.name, style = MaterialTheme.typography.titleSmall)
                        Text(
                            "$timeText ${stringResource(R.string.ago)}",
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1
                        )
                    }

                    when (item) {
                        is ScannedItem.JpgItem -> Icon(
                            painterResource(R.drawable.jpeg_icon),
                            contentDescription = "File extension"
                        )

                        is ScannedItem.PdfFile -> Icon(
                            painterResource(R.drawable.pdf_icon),
                            contentDescription = "File extension"
                        )

                        is TestItem -> Icon(item.icon, "")
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
    val context = LocalContext.current
    val scans = listOf<ScannedItem>(
        TestItem(context),
        TestItem(context),
        TestItem(context),
        TestItem(context),
        TestItem(context)
    )
    SimpleScannerTheme {
        ScansCarousel(
            ScansUiState.Success(
                scans = scans,
                timeNow = Clock.System.now(),
            )
        )
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
        )
    }
}

@Composable
@DarkLightPreview
fun LoadingScansCarouselPreview() {
    SimpleScannerTheme {
        ScansCarousel(ScansUiState.Loading)
    }
}

@OptIn(ExperimentalTime::class)
sealed interface ScansUiState {
    object Loading : ScansUiState
    object Error : ScansUiState

    object Empty : ScansUiState

    data class Success(val scans: List<ScannedItem>, val timeNow: Instant) : ScansUiState {
        init {
            require(scans.isNotEmpty())
        }
    }
}
