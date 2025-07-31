package su.pank.simplescanner.domain

import android.app.Activity
import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import su.pank.simplescanner.data.preferences.UserPreferencesRepository
import javax.inject.Inject

class CheckGoogleServicesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    @ApplicationContext private val context: Context
) {
    private val googleApiAvailability = GoogleApiAvailability.getInstance()




    suspend operator fun invoke(): CheckResult {
        val prefs = userPreferencesRepository.userPreferences.first()
        if (prefs.hasGoogleAPI) {
            return CheckResult.OK
        }
        val code = googleApiAvailability.isGooglePlayServicesAvailable(context)
        if (code != ConnectionResult.SUCCESS) {
            return if (googleApiAvailability.isUserResolvableError(code)) {
                CheckResult.RESOLVABLE
            } else {
                CheckResult.ERROR
            }
        }
        val client = GmsDocumentScanning.getClient(GmsDocumentScannerOptions.DEFAULT_OPTIONS)
        try {

            client.getStartScanIntent(context as Activity).await()
            userPreferencesRepository.googleApiChecked()
            return CheckResult.OK
        } catch (_: Exception) {
            return CheckResult.RESOLVABLE
        }
    }

    enum class CheckResult {
        OK,
        RESOLVABLE, // Need install or update Google Services
        ERROR // IDK
    }
}