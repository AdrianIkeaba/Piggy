package com.ghostdev.piggy.presentation.view.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ghostdev.piggy.data.repository.PiggyRepo
import com.ghostdev.piggy.presentation.view.viewmodel.PiggyViewModel

class PiggyViewModelFactory(private val piggyRepo: PiggyRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PiggyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PiggyViewModel(piggyRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
