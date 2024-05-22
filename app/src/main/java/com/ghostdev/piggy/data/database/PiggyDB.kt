package com.ghostdev.piggy.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [PiggyModel::class], version = 1)
abstract class PiggyDB: RoomDatabase() {
    abstract fun piggyDao(): PiggyDao

    companion object {
        @Volatile
        private var INSTANCE: PiggyDB? = null
        fun getInstance(context: Context): PiggyDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = databaseBuilder(
                        context,
                        PiggyDB::class.java,
                        "piggy_db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}