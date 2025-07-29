package su.pank.simplescanner.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import su.pank.simplescanner.ui.ui.MainView

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleScannerTheme {
                MainView({

                }){}
            }
        }
    }
}



