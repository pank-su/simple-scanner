package su.pank.simplescanner.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import su.pank.simplescanner.R
import su.pank.simplescanner.data.models.Scan
import su.pank.simplescanner.data.models.testScan
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import su.pank.simplescanner.utils.SharedElementScopeCompositionLocal
import su.pank.simplescanner.utils.rememberTimeFormatter
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalTime::class)
@Composable
fun ScanList(
    state: ScansUiState,
    onClickedScan: (Scan) -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues.Zero
) {
    Box(
        modifier.fillMaxSize()
    ) {

        when (state) {
            is ScansUiState.Success -> {
                SuccessState(state.scans, state.timeNow, onClickedScan, paddingValues)
            }

            ScansUiState.Empty, ScansUiState.Error -> EmptyState()
            ScansUiState.Loading -> LoadingState()
        }


    }
}

@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
@Composable
private fun SuccessState(
    scans: List<Scan>,
    timeNow: Instant,
    onClickedScan: (Scan) -> Unit,
    paddingValues: PaddingValues
) {
    val timeFormatter = rememberTimeFormatter()
    val context = LocalContext.current



    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), contentPadding = paddingValues) {
        item {
            Spacer(Modifier.height(10.dp))
        }
        items(scans) { item ->
            val timeText by remember(timeNow) {
                derivedStateOf {
                    timeFormatter.formatDuration(timeNow - item.savedAt)
                }
            }
            ElevatedCard(
                onClick = { onClickedScan(item) },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PageCarousel(
                        item.imageRequests(context).map { it.build() },
                        modifier = Modifier.width(200.dp),
                        withPager = false
                    )
                    Spacer(Modifier.width(10.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Text(
                            item.name,
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.End
                        )
                        Text(
                            "$timeText ${stringResource(R.string.ago)}",
                            style = MaterialTheme.typography.titleSmall,
                            maxLines = 1,
                            textAlign = TextAlign.End
                        )
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
}

@OptIn(ExperimentalTime::class)
@Preview
@Composable
fun ScanListPreview() {
    val state =
        ScansUiState.Success(scans = listOf(testScan, testScan), timeNow = Clock.System.now())
    SimpleScannerTheme {
        SharedElementScopeCompositionLocal {
            ScanList(state = state, onClickedScan = {})
        }
    }
}
