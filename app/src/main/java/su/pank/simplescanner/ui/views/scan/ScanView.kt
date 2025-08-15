package su.pank.simplescanner.ui.views.scan

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import coil3.request.crossfade
import kotlinx.serialization.Serializable
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.ui.components.PageCarousel
import su.pank.simplescanner.utils.serializableType
import kotlin.reflect.typeOf


@Serializable
data class Scan(val scannedItem: ScannedItem) {
    companion object {
        val typeMap = mapOf(typeOf<ScannedItem>() to serializableType<ScannedItem>())
        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<Scan>(typeMap)
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ScanRoute(scanViewModel: ScanViewModel = hiltViewModel()) {
    ScanView(scanViewModel.scanItem)
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ScanView(item: ScannedItem, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val images = remember {
        item.imageRequests(context).map { it.crossfade(true).build() }
    }
    Scaffold {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(12.dp)
        ) {
            PageCarousel(
                pages = images,
                when (item) {
                    is ScannedItem.JpgItem -> item.files.first()
                    is ScannedItem.PdfFile -> item.file + "$it"
                },

                Modifier.weight(1f)
            )
        }
    }

}





