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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.ghostdev.piggy.data.database.PiggyDB
import com.ghostdev.piggy.data.navigation.NavGraph
import com.ghostdev.piggy.data.repository.PiggyRepo
import com.ghostdev.piggy.presentation.theme.PiggyTheme
import com.ghostdev.piggy.presentation.view.viewmodel.PiggyViewModel
import com.ghostdev.piggy.presentation.view.viewmodel.ProvidePiggyViewModel
import com.ghostdev.piggy.presentation.view.viewmodel.viewmodelfactory.PiggyViewModelFactory

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

            ProvidePiggyViewModel(piggyViewModel) {
                NavGraph(controller = navController)
            }
        }
    }
}
