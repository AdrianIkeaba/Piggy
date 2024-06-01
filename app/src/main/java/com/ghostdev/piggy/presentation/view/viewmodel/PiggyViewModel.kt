package com.ghostdev.piggy.presentation.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghostdev.piggy.data.database.PiggyModel
import com.ghostdev.piggy.data.repository.PiggyRepo
import com.ghostdev.piggy.presentation.view.viewmodel.viewmodelfactory.PiggyViewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PiggyViewModel(private val piggyRepository: PiggyRepo): ViewModel() {
    private val _piggyList = MutableStateFlow<List<PiggyModel>>(emptyList())
    val piggyList: StateFlow<List<PiggyModel>> = _piggyList

    private val _piggyItem = MutableStateFlow<PiggyModel?>(null)
    val piggyItem: StateFlow<PiggyModel?> = _piggyItem

    fun createPiggy(piggy: PiggyModel) {
        viewModelScope.launch {
            piggyRepository.addPiggyBank(piggy)
            getAllPiggyBanks()
        }
    }

    fun updatePiggy(piggy: PiggyModel) {
        viewModelScope.launch {
            piggyRepository.updatePiggyBank(piggy)
            getAllPiggyBanks()
        }
    }

    fun deletePiggy(piggy: PiggyModel) {
        viewModelScope.launch {
            piggyRepository.deletePiggyBank(piggy)
            getAllPiggyBanks()
        }
    }

    fun pinPiggy(piggy: PiggyModel) {
        viewModelScope.launch {
            piggyRepository.pinPiggyBank(piggy)
            getAllPiggyBanks()
        }
    }

    fun unpinPiggy(piggy: PiggyModel) {
        viewModelScope.launch {
            piggyRepository.unpinPiggyBank(piggy)
            getAllPiggyBanks()
        }
    }

    fun getPiggyBank(id: Int) {
        viewModelScope.launch {
            _piggyItem.value = piggyRepository.getPiggyBank(id)
        }
    }

    fun getAllPiggyBanks() {
        viewModelScope.launch {
            _piggyList.value = piggyRepository.getAllPiggyBanks()
        }
    }

    //Filters and Sorts

    fun orderByNameAsc() {
        viewModelScope.launch {
            _piggyList.value = piggyRepository.orderByNameAsc()
        }
    }

    fun orderByNameDesc() {
        viewModelScope.launch {
            _piggyList.value = piggyRepository.orderByNameDesc()
        }
    }

    fun orderByAmountAsc() {
        viewModelScope.launch {
            _piggyList.value = piggyRepository.orderByAmountAsc()
        }
    }

    fun orderByAmountDesc() {
        viewModelScope.launch {
            _piggyList.value = piggyRepository.orderByAmountDesc()
        }
    }

    fun orderByGoalAsc() {
        viewModelScope.launch {
            _piggyList.value = piggyRepository.orderByGoalAsc()
        }
    }

    fun orderByGoalDesc() {
        viewModelScope.launch {
            _piggyList.value = piggyRepository.orderByGoalDesc()
        }
    }

    fun orderByRemainingAsc() {
        viewModelScope.launch {
            _piggyList.value = piggyRepository.orderByRemainingAsc()
        }
    }

    fun orderByRemainingDesc() {
        viewModelScope.launch {
            _piggyList.value = piggyRepository.orderByRemainingDesc()
        }
    }


    init {
        getAllPiggyBanks()
    }

}