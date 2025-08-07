package su.pank.simplescanner.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import su.pank.simplescanner.R
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.data.models.TestItem
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import su.pank.simplescanner.utils.rememberTimeFormatter
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun ScansCarousel(scans: List<ScannedItem>, timeNow: Instant, modifier: Modifier = Modifier) {

    val timeFormatter = rememberTimeFormatter()

    Box(
        modifier
            .height(211.dp)
            .widthIn(300.dp)
    ) {
        if (scans.isEmpty()) {
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
            return
        }
        val state = rememberCarouselState { scans.size }


        HorizontalMultiBrowseCarousel(state, preferredItemWidth = 145.dp, itemSpacing = 10.dp) {
            val item = scans[it]
            val timeText by remember(timeNow) {
                derivedStateOf {
                    timeFormatter.formatDuration(timeNow - item.savedAt)
                }
            }

            ElevatedCard(
                modifier = Modifier
                    .fillMaxSize()
                    .maskClip(MaterialTheme.shapes.medium)
            ) {
                Column(
                    Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                ) {
                    Image(
                        item.preview,
                        contentDescription = "pdf image",
                        modifier = Modifier
                            .weight(1f)
                            .clip(
                                RoundedCornerShape(4.dp)
                            ),
                        contentScale = ContentScale.Crop
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(item.name, style = MaterialTheme.typography.titleSmall)
                            // TODO: Replace with date period
                            Text("$timeText ${stringResource(R.string.ago)}", style = MaterialTheme.typography.bodySmall)
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
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
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
            scans = scans,
            timeNow = Clock.System.now(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
