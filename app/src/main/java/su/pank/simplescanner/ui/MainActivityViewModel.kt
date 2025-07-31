package su.pank.simplescanner.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import su.pank.simplescanner.domain.CheckGoogleServicesUseCase
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val checkGoogleServicesUseCase: CheckGoogleServicesUseCase) :
    ViewModel() {
    val state = flow {
        emit(
            when (checkGoogleServicesUseCase()) {
                CheckGoogleServicesUseCase.CheckResult.OK -> MainActivityState.Success
                CheckGoogleServicesUseCase.CheckResult.RESOLVABLE -> MainActivityState.NeedUpdateGoogleServices
                CheckGoogleServicesUseCase.CheckResult.ERROR -> MainActivityState.Error("")
            }
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        initialValue = MainActivityState.Loading,
    )
}

/**
 * State of MainActivity
 */
sealed interface MainActivityState {
    /**
     * Wait checks and data loading
     */
    object Loading : MainActivityState

    /**
     * Data loads success
     */
    object Success : MainActivityState


    object NeedUpdateGoogleServices : MainActivityState

    /**
     * Show error dialog with message
     */
    data class Error(val message: String) : MainActivityState


}