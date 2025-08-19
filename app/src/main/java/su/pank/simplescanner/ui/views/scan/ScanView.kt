package su.pank.simplescanner.ui.views.scan

import android.os.Build
import android.text.format.DateFormat
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.toRoute
import coil3.request.crossfade
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.serialization.Serializable
import su.pank.simplescanner.R
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.ui.components.PageCarousel
import su.pank.simplescanner.utils.serializableType
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlin.reflect.typeOf
import kotlin.time.ExperimentalTime
import kotlin.time.toJavaInstant


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
fun ScanRoute(scanViewModel: ScanViewModel = hiltViewModel(), onBackPressed: () -> Unit) {
    val item by scanViewModel.scanItem.collectAsStateWithLifecycle(null)
    if (item == null) return
    ScanView(item!!, onBackPressed)
}


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class, ExperimentalTime::class
)
@Composable
fun ScanView(item: ScannedItem, onBackPressed: () -> Unit, modifier: Modifier = Modifier) {
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
                when (item) {
                    is ScannedItem.JpgItem -> item.files.first()
                    is ScannedItem.PdfFile -> item.file
                },

                Modifier.weight(1f)
            )
        }
    }

}





