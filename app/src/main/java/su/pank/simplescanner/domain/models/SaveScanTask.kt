package su.pank.simplescanner.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class SaveScanTask(val scanId: Int, val files: List<String>)
