package su.pank.simplescanner.domain

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import su.pank.simplescanner.data.settings.SettingsRepository
import javax.inject.Inject

class CheckGoogleServicesUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
    @ApplicationContext private val context: Context
) {
    private val googleApiAvailability = GoogleApiAvailability.getInstance()

    suspend operator fun invoke(activity: Activity? = null): CheckResult {
        val prefs = settingsRepository.userPreferences.first()
        Log.d("CheckGoogleServicesUseCase", "hasGoogleApi: ${prefs.hasGoogleAPI}")
        if (prefs.hasGoogleAPI) {
            return CheckResult.OK
        }
        val code = googleApiAvailability.isGooglePlayServicesAvailable(context)
        Log.d("CheckGoogleServicesUseCase", "isGooglePlayServicesAvailable: $code")

        if (code != ConnectionResult.SUCCESS) {
            return if (googleApiAvailability.isUserResolvableError(code)) {
                CheckResult.RESOLVABLE
            } else {
                CheckResult.ERROR
            }
        }
        val client = GmsDocumentScanning.getClient(GmsDocumentScannerOptions.DEFAULT_OPTIONS)
        return try {
            if (activity != null) {
                client.getStartScanIntent(activity).await()
                settingsRepository.googleApiChecked()
                CheckResult.OK
            } else {
                CheckResult.RESOLVABLE
            }
        } catch (_: Exception) {
            CheckResult.RESOLVABLE
        }
    }

    enum class CheckResult {

        OK,
        RESOLVABLE, // Need install or update Google Services
        ERROR // IDK
    }
}