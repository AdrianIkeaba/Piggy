package com.ghostdev.piggy.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface PiggyDao {

    @Insert
    suspend fun addPiggyBank(piggyData: PiggyModel)

    @Update
    suspend fun updatePiggyBank(piggyData: PiggyModel)

    @Delete
    suspend fun deletePiggyBank(piggyData: PiggyModel)

    @Update
    suspend fun pinPiggyBank(piggyData: PiggyModel)

}