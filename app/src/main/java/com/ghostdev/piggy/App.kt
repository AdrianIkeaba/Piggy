package com.ghostdev.piggy

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.ghostdev.piggy.data.database.PiggyDB
import com.ghostdev.piggy.data.repository.PiggyRepo
import com.ghostdev.piggy.presentation.theme.PiggyTheme
import com.ghostdev.piggy.presentation.view.ui.home.HomeScreen
import com.ghostdev.piggy.presentation.view.ui.onboarding.OnboardingScreen
import com.ghostdev.piggy.presentation.view.viewmodel.PiggyViewModel
import com.ghostdev.piggy.presentation.view.viewmodel.ProvidePiggyViewModel
import com.ghostdev.piggy.presentation.view.viewmodel.viewmodelfactory.PiggyViewModelFactory
import com.ghostdev.piggy.utils.PreferencesHelper


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.getWindowInsetsController()!!
            .setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS)
        setContent {
            PiggyApp()
        }
    }
}

@Composable
fun PiggyApp() {
    PiggyTheme {
        Surface {
            val context = LocalContext.current.applicationContext
            val piggyDB = PiggyDB.getInstance(context)
            val piggyRepo = PiggyRepo(piggyDB)
            val piggyViewModel = viewModel(factory = PiggyViewModelFactory(piggyRepo), modelClass = PiggyViewModel::class.java)
            val navController = rememberNavController()

            val isFirstTime = remember { mutableStateOf(PreferencesHelper.isFirstTime(context)) }

            LaunchedEffect(Unit) {
                if (isFirstTime.value) {
                    // Set the flag to false so the first screen won't show next time
                    PreferencesHelper.setFirstTime(context, false)
                }
            }

            if (isFirstTime.value) {
                ProvidePiggyViewModel(piggyViewModel) {
                    HomeScreen()
                }
            } else {
                ProvidePiggyViewModel(piggyViewModel) {
                    OnboardingScreen(controller = navController)
                }
            }

        }
    }
}
