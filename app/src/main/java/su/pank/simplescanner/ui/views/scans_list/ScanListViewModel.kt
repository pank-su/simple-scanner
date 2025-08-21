package su.pank.simplescanner.ui.views.scans_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import su.pank.simplescanner.data.scans.ScansRepository
import su.pank.simplescanner.ui.components.ScansUiState
import su.pank.simplescanner.utils.timeFlow
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@HiltViewModel
class ScanListViewModel @Inject constructor(
    scansRepository: ScansRepository,
) : ViewModel() {
    val scansUiState = combine(scansRepository.scans, timeFlow(1.seconds)) { scans, time ->
        if (scans.isEmpty()) {
            return@combine ScansUiState.Empty
        }
        ScansUiState.Success(scans.sortedByDescending { it.savedAt }, time)
    }.catch { ScansUiState.Error }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ScansUiState.Loading
    )

}