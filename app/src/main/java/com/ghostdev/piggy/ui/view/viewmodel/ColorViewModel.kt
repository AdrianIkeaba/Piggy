package com.ghostdev.piggy.ui.view.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ColorViewModel : ViewModel() {
    private val _selectedColor = MutableStateFlow<Color?>(null)
    val selectedColor: StateFlow<Color?> = _selectedColor

    fun selectColor(color: Color) {
        _selectedColor.value = color
    }

    fun resetColor() {
        _selectedColor.value = null
    }
}