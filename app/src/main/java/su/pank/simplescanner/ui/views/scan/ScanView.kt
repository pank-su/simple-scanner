package su.pank.simplescanner.ui.views.scan

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.toRoute
import coil3.request.crossfade
import kotlinx.serialization.Serializable
import su.pank.simplescanner.R
import su.pank.simplescanner.data.models.Scan
import su.pank.simplescanner.data.models.testScan
import su.pank.simplescanner.ui.components.PageCarousel
import su.pank.simplescanner.utils.SharedElementScopeCompositionLocal
import su.pank.simplescanner.utils.serializableType
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlin.reflect.typeOf
import kotlin.time.ExperimentalTime
import kotlin.time.toJavaInstant
import kotlin.uuid.ExperimentalUuidApi


@Serializable
data class ScanMessage(val scan: Scan) {
    companion object {
        val typeMap = mapOf(typeOf<Scan>() to serializableType<Scan>())
        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<ScanMessage>(typeMap)
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ScanRoute(scanViewModel: ScanViewModel = hiltViewModel(), onBackPressed: () -> Unit) {
    val item by scanViewModel.scanItem.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ScanView(item, onBackPressed, onShare = { scanViewModel.shareScan(context) })
}


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class, ExperimentalTime::class, ExperimentalUuidApi::class
)
@Composable
fun ScanView(
    item: Scan,
    onBackPressed: () -> Unit,
    onShare: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val images = remember(item) {
        item.imageRequests(context).map { it.crossfade(true).build() }
    }
    val dateFormat = remember{
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    }
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(item.name)
        }, subtitle = {
            Text(stringResource(R.string.saved_at) + " " +  dateFormat.format(item.savedAt.toJavaInstant().atZone(
                ZoneId.systemDefault())))
        }, navigationIcon = {
            IconButton(onBackPressed, shapes = IconButtonDefaults.shapes()) {
                Icon(painterResource(R.drawable.back_icon), "Back")
            }
        }, actions = {
            IconButton(onShare, shapes = IconButtonDefaults.shapes()) {
                Icon(painterResource(R.drawable.share_icon), "Share")
            }
        })
    }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(12.dp)
        ) {
            PageCarousel(
                pages = images,
                item.id.toHexString(),

                Modifier.weight(1f)
            )
        }
    }

}

@OptIn(
    ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class, ExperimentalTime::class, ExperimentalUuidApi::class
)
@Preview
@Composable
fun ScanViewPreview() {
    SharedElementScopeCompositionLocal {

        ScanView(
            item = testScan,
            onBackPressed = {}, {}
        )
    }
}
