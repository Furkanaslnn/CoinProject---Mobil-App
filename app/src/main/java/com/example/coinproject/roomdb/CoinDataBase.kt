package com.example.coinproject.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coinproject.model.Coin

@Database(entities = [Coin::class], version = 1)
abstract class CoinDataBase : RoomDatabase() {
    abstract fun coinDao() : CoinDAO

    companion object {

        @Volatile
        private var instance: CoinDataBase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CoinDataBase::class.java,
            "coindatabase"
        ).build()

    }
}