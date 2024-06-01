package com.ghostdev.piggy.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "piggy_table")
data class PiggyModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo("name")
    val piggyName: String,

    @ColumnInfo("saved")
    val amountSaved: Double,

    @ColumnInfo("goal")
    val goal: Double,

    @ColumnInfo("deadline")
    val deadlineDate: String,

    @ColumnInfo("color")
    val color: Int,

    @ColumnInfo("pin")
    val isPinned: Boolean
)