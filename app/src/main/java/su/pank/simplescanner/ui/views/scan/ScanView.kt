package su.pank.simplescanner.ui.views.scan

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.utils.serializableType
import kotlin.reflect.typeOf


@Serializable
data class Scan(val scannedItem: ScannedItem){
    companion object {
        val typeMap = mapOf(typeOf<ScannedItem>() to serializableType<ScannedItem>())
        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<Scan>(typeMap)
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ScanRoute(scanViewModel: ScanViewModel = hiltViewModel()){
    ScanView()
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ScanView(modifier: Modifier = Modifier, ) {

}

