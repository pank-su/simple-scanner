package su.pank.simplescanner.ui.views.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.time.delay
import su.pank.simplescanner.data.scans.ScansRepository
import su.pank.simplescanner.proto.ScansSettings
import su.pank.simplescanner.ui.components.ScansUiState
import javax.inject.Inject
import kotlin.time.Clock
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@HiltViewModel
class MainViewModel @Inject constructor(private val scansRepository: ScansRepository) :
    ViewModel() {

    val scansUiState = combine(scansRepository.scans, timeFlow()){
        scans, time ->
        if (scans.isEmpty()){
            return@combine ScansUiState.Empty
        }
        ScansUiState.Success(scans, time)
    }.catch { ScansUiState.Error }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            ScansUiState.Loading
        )

    private fun timeFlow() = flow {
        while (true) {
            emit(Clock.System.now())
            delay(1.seconds)
        }
    }

}


sealed interface ScansSettingsUiState{
    object Loading: ScansSettingsUiState
    object Error: ScansSettingsUiState
    data class Success(val settings: ScansSettings): ScansSettingsUiState
}