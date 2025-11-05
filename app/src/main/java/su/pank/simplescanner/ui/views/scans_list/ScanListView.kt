package su.pank.simplescanner.ui.views.scans_list

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import su.pank.simplescanner.R
import su.pank.simplescanner.data.models.Scan
import su.pank.simplescanner.ui.components.ScanList
import su.pank.simplescanner.ui.components.ScansUiState


@Serializable
data object ScanList : NavKey

@Composable
fun ScanListRoute(
    onScanSelect: (Scan) -> Unit,
    onBackHandler: () -> Unit,
    viewModel: ScanListViewModel = hiltViewModel()
) {
    val scansUiState by viewModel.scansUiState.collectAsStateWithLifecycle()

    ScanListView(state = scansUiState, onScanSelect)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanListView(state: ScansUiState, onScanSelect: (Scan) -> Unit) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                stringResource(R.string.recent_title),
            )
        }, scrollBehavior = scrollBehavior)
    }, modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)) {
        ScanList(
            state, onScanSelect, modifier = Modifier
                .consumeWindowInsets(it), paddingValues = it
        )
    }

}