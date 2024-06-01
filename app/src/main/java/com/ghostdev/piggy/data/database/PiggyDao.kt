package com.ghostdev.piggy.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
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

    @Update
    suspend fun unpinPiggyBank(piggyData: PiggyModel)

    @Query("SELECT * FROM piggy_table")
    suspend fun getAllPiggyBank(): List<PiggyModel>

    @Query("SELECT * FROM piggy_table WHERE id = :id")
    suspend fun getPiggyBankById(id: Int): PiggyModel


    //Filters and Sorts
    @Query("SELECT * FROM piggy_table ORDER BY name ASC")
    suspend fun orderByNameAsc(): List<PiggyModel>

    @Query("SELECT * FROM piggy_table ORDER BY name DESC")
    suspend fun orderByNameDesc(): List<PiggyModel>

    @Query("SELECT * FROM piggy_table ORDER BY saved ASC")
    suspend fun orderByAmountAsc(): List<PiggyModel>

    @Query("SELECT * FROM piggy_table ORDER BY saved DESC")
    suspend fun orderByAmountDesc(): List<PiggyModel>

    @Query("SELECT * FROM piggy_table ORDER BY goal ASC")
    suspend fun orderByGoalAsc(): List<PiggyModel>
    @Query("SELECT * FROM piggy_table ORDER BY goal DESC")
    suspend fun orderByGoalDesc(): List<PiggyModel>

    @Query("SELECT * FROM piggy_table ORDER BY goal - saved ASC")
    suspend fun orderByRemainingAsc(): List<PiggyModel>
    @Query("SELECT * FROM piggy_table ORDER BY goal - saved DESC")
    suspend fun orderByRemainingDesc(): List<PiggyModel>


}