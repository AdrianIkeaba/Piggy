package com.ghostdev.piggy.data.navigation

sealed class NavDestinations(val route: String) {

    data object Onboarding : NavDestinations("onboarding")
    data object Home : NavDestinations("home")

}