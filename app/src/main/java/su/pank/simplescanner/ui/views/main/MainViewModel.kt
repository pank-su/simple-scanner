package su.pank.simplescanner.ui.views.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.data.scans.ScansRepository
import su.pank.simplescanner.proto.ScansSettings
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val scansRepository: ScansRepository) :
    ViewModel() {

    val scansUiState =
        scansRepository.scans.map { ScansUiState.Success(it) }.catch { ScansUiState.Error }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            ScansUiState.Loading
        )

}

sealed interface ScansUiState {
    object Loading : ScansUiState
    object Error : ScansUiState
    data class Success(val scans: List<ScannedItem>) : ScansUiState {
        fun isEmpty() = scans.isEmpty()
    }
}
val EmptyScansUiState = ScansUiState.Success(listOf())

sealed interface ScansSettingsUiState{
    object Loading: ScansSettingsUiState
    object Error: ScansSettingsUiState
    data class Success(val settings: ScansSettings): ScansSettingsUiState
}