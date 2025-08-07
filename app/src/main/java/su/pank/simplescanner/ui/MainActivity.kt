package su.pank.simplescanner.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import su.pank.simplescanner.R
import su.pank.simplescanner.ui.nav.ScannerNavHost
import su.pank.simplescanner.ui.splash.Splash
import su.pank.simplescanner.ui.views.splash_error.SplashError
import su.pank.simplescanner.ui.theme.SimpleScannerTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { viewModel.state.value == MainActivityState.Loading }

        viewModel.checkGoogleServicesAndLoadData(this)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            val state by viewModel.state.collectAsState()

            val updateString = stringResource(R.string.error_no_services)

            SimpleScannerTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface)
                )
                // TODO: NEED SCREENS TO SHOW STATE
                ScannerNavHost(onRestart = ::restartApp, navController = navController)

                LaunchedEffect(state) {
                    if (state is MainActivityState.Loading) {
                        return@LaunchedEffect
                    }
                    navController.navigate(
                        when (state) {
                            is MainActivityState.Error -> SplashError((state as MainActivityState.Error).message)
                            MainActivityState.Loading -> Splash
                            MainActivityState.NeedInstallGoogleServices -> SplashError(updateString) // TODO: maybe use update google services dialog
                            is MainActivityState.Success -> Splash
                        }
                    )
                }


            }
        }
    }

    fun restartApp() {
        val intent: Intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
        this.finishAffinity()
    }


}



