package com.ghostdev.piggy.data.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ghostdev.piggy.presentation.view.ui.home.HomeScreen
import com.ghostdev.piggy.presentation.view.ui.onboarding.OnboardingScreen
import com.ghostdev.piggy.utils.PreferencesHelper

@Composable
fun NavGraph(controller: NavHostController) {
    val context = LocalContext.current.applicationContext
    val isFirstTime = PreferencesHelper.isFirstTime(context)

    NavHost(navController = controller, startDestination = if (isFirstTime) NavDestinations.Onboarding.toString() else NavDestinations.Home.toString()) {
        composable(NavDestinations.Onboarding.toString()) {
            OnboardingScreen(controller)
        }
        composable(NavDestinations.Home.toString()) {
            HomeScreen()
        }
    }
}