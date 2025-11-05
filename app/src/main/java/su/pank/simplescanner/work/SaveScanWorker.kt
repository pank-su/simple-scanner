package su.pank.simplescanner.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.serialization.json.Json
import su.pank.simplescanner.domain.SaveScanUseCase
import su.pank.simplescanner.domain.models.SaveScanTask

@HiltWorker
class SaveScanWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val saveScanUseCase: SaveScanUseCase
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        requireNotNull(inputData.getString("data"))
        val scan = Json.decodeFromString<SaveScanTask>(inputData.getString("data")!!)
        saveScanUseCase(scan)
        return Result.success()
    }


}