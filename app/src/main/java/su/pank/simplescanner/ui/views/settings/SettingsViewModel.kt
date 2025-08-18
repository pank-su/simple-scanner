package su.pank.simplescanner.ui.views.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import su.pank.simplescanner.data.models.ScanExtension
import su.pank.simplescanner.data.models.ScansSettings
import su.pank.simplescanner.data.scan_settings.ScanSettingsRepository
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val scanSettingsRepository: ScanSettingsRepository) :
    ViewModel() {
    val settingsUiState = scanSettingsRepository.settings.map {
        SettingsUiState.Success(it)

    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        SettingsUiState.Loading
    )

    fun setExtension(extension: ScanExtension) {
        viewModelScope.launch {
            scanSettingsRepository.selectExtension(extension)
        }
    }


}

sealed interface SettingsUiState {
    object Loading : SettingsUiState
    data class Success(val settings: ScansSettings) : SettingsUiState
}