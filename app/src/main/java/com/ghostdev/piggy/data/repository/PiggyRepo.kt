package com.ghostdev.piggy.data.repository

import com.ghostdev.piggy.data.database.PiggyDB
import com.ghostdev.piggy.data.database.PiggyModel

class PiggyRepo(private val piggyDB: PiggyDB) {

    suspend fun addPiggyBank(piggyData: PiggyModel) {
        piggyDB.piggyDao().addPiggyBank(piggyData)
    }

    suspend fun updatePiggyBank(piggyData: PiggyModel) {
        piggyDB.piggyDao().updatePiggyBank(piggyData)
    }

    suspend fun deletePiggyBank(piggyData: PiggyModel) {
        piggyDB.piggyDao().deletePiggyBank(piggyData)
    }

    suspend fun pinPiggyBank(piggyData: PiggyModel) {
        piggyDB.piggyDao().pinPiggyBank(piggyData)
    }

    suspend fun unpinPiggyBank(piggyData: PiggyModel) {
        piggyDB.piggyDao().unpinPiggyBank(piggyData)
    }

    suspend fun getPiggyBank(id: Int): PiggyModel {
        return piggyDB.piggyDao().getPiggyBankById(id)
    }

    suspend fun getAllPiggyBanks(): List<PiggyModel> {
        return piggyDB.piggyDao().getAllPiggyBank()
    }

    // Sorting functions
    suspend fun orderByNameAsc(): List<PiggyModel> {
        return piggyDB.piggyDao().orderByNameAsc()
    }

    suspend fun orderByNameDesc(): List<PiggyModel> {
        return piggyDB.piggyDao().orderByNameDesc()
    }

    suspend fun orderByAmountAsc(): List<PiggyModel> {
        return piggyDB.piggyDao().orderByAmountAsc()
    }

    suspend fun orderByAmountDesc(): List<PiggyModel> {
        return piggyDB.piggyDao().orderByAmountDesc()
    }

    suspend fun orderByGoalAsc(): List<PiggyModel> {
        return piggyDB.piggyDao().orderByGoalAsc()
    }

    suspend fun orderByGoalDesc(): List<PiggyModel> {
        return piggyDB.piggyDao().orderByGoalDesc()
    }

    suspend fun orderByRemainingAsc(): List<PiggyModel> {
        return piggyDB.piggyDao().orderByRemainingAsc()
    }

    suspend fun orderByRemainingDesc(): List<PiggyModel> {
        return piggyDB.piggyDao().orderByRemainingDesc()
    }
}
