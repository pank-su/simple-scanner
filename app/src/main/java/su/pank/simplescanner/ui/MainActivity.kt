package su.pank.simplescanner.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import su.pank.simplescanner.ui.theme.SimpleScannerTheme
import su.pank.simplescanner.ui.ui.MainView

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { viewModel.state.value == MainActivityState.Loading  }

        viewModel.checkGoogleServicesAndLoadData(this)
        enableEdgeToEdge()
        setContent {

            val state by viewModel.state.collectAsState()
            SimpleScannerTheme {
                // TODO: NEED SCREENS TO SHOW STATE
                when (state) {
                    is MainActivityState.Error -> Text("ERROR")
                    MainActivityState.Loading -> Text("LOADING")
                    MainActivityState.NeedInstallGoogleServices -> Text("UPDATE PLEASE")
                    is MainActivityState.Success -> MainView({

                    }) {}
                }


            }
        }
    }


}



