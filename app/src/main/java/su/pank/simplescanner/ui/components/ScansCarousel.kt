package su.pank.simplescanner.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import su.pank.simplescanner.data.models.ScannedItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScansCarousel(scans: List<ScannedItem>){
    val state = rememberCarouselState { scans.size }


    HorizontalMultiBrowseCarousel(state, preferredItemWidth = 145.dp) {

    }
}