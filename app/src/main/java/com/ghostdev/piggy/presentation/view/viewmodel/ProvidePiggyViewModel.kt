package com.ghostdev.piggy.presentation.view.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalPiggyViewModel = staticCompositionLocalOf<PiggyViewModel> {
    error("No PiggyViewModel provided")
}

@Composable
fun ProvidePiggyViewModel(viewModel: PiggyViewModel, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalPiggyViewModel provides viewModel, content = content)
}
