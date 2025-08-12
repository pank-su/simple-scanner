package su.pank.simplescanner.ui.views.scan

import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import su.pank.simplescanner.data.models.ScannedItem
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(savedStateHandle: SavedStateHandle): ViewModel() {
    val scanItem: ScannedItem = Scan.from(savedStateHandle).scannedItem

}

