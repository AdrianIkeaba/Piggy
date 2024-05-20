package com.ghostdev.piggy.ui.view.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BottomSheetViewModel : ViewModel() {
    private val _selectedLeftButton = MutableStateFlow("Name")
    val selectedLeftButton: StateFlow<String> = _selectedLeftButton

    private val _selectedRightButton = MutableStateFlow("Ascending")
    val selectedRightButton: StateFlow<String> = _selectedRightButton

    fun selectLeftButton(button: String) {
        _selectedLeftButton.value = button
    }

    fun selectRightButton(button: String) {
        _selectedRightButton.value = button
    }
}