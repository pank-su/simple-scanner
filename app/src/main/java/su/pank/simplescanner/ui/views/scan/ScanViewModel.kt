package su.pank.simplescanner.ui.views.scan

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import su.pank.simplescanner.data.models.ScannedItem
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(savedStateHandle: SavedStateHandle): ViewModel() {
    private val scanItem: ScannedItem = Scan.from(savedStateHandle).scannedItem
}