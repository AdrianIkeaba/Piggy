package com.ghostdev.piggy.data.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ghostdev.piggy.ui.view.HomeScreen
import com.ghostdev.piggy.ui.view.OnboardingScreen

@Composable
fun NavGraph(controller: NavHostController) {
    NavHost(navController = controller, startDestination = NavDestinations.Onboarding.toString()) {
        composable(NavDestinations.Onboarding.toString()) {
            OnboardingScreen(controller)
        }
        composable(NavDestinations.Home.toString()) {
            HomeScreen(controller)
        }
    }
}