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
import androidx.navigation.compose.rememberNavController
import com.ghostdev.piggy.data.navigation.NavGraph
import com.ghostdev.piggy.ui.theme.PiggyTheme
import com.ghostdev.piggy.ui.view.HomeScreen
import com.ghostdev.piggy.ui.view.OnboardingScreen


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
            val navController = rememberNavController()
            NavGraph(navController)
        }
    }
}
