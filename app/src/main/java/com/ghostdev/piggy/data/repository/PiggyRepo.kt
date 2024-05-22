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
        piggyDB.piggyDao().pinPiggyBank(piggyData)
    }
}