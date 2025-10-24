package su.pank.simplescanner.ui

import android.app.Activity
import androidx.datastore.core.IOException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import su.pank.simplescanner.R
import su.pank.simplescanner.data.models.AppSettings
import su.pank.simplescanner.data.settings.AppSettingsRepository
import su.pank.simplescanner.domain.CheckGoogleServicesUseCase
import su.pank.simplescanner.utils.ResourceProvider
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val checkGoogleServicesUseCase: CheckGoogleServicesUseCase,
    private val appSettingsRepository: AppSettingsRepository,
    private val resourceProvider: ResourceProvider
) :
    ViewModel() {
    private val _state = MutableStateFlow<MainActivityState>(MainActivityState.Loading)
    val state = _state.asStateFlow()

    suspend fun loadData() = appSettingsRepository.settings.first()


    fun checkGoogleServicesAndLoadData(activity: Activity) {
        viewModelScope.launch {
            val result = checkGoogleServicesUseCase(activity)
            _state.value =
                when (result) {
                    CheckGoogleServicesUseCase.CheckResult.OK -> {
                        try {
                            val data = loadData()
                            MainActivityState.Success(data)

                        } catch (_: IOException) {
                            MainActivityState.Error(resourceProvider.getString(R.string.error_unknown))
                        }
                    }

                    CheckGoogleServicesUseCase.CheckResult.RESOLVABLE -> MainActivityState.NeedInstallGoogleServices
                    CheckGoogleServicesUseCase.CheckResult.ERROR -> MainActivityState.Error(
                        resourceProvider.getString(R.string.error_unknown)
                    )
                }

        }
    }
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
    data class Success(val prefs: AppSettings) : MainActivityState


    object NeedInstallGoogleServices : MainActivityState

    /**
     * Show error dialog with message
     */
    data class Error(val message: String) : MainActivityState


}