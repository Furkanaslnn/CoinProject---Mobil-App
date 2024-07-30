package com.example.coinproject.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.coinproject.model.Coin

@Dao
interface CoinDAO {

    @Insert
    suspend fun insertAll(vararg coin: Coin) : List<Long>
    //eklediği besinlerin id sini lonk olarak geri gönderiyor

    @Query("SELECT * FROM coin")
    suspend fun getAllCoin(): List<Coin>

    @Query("SELECT * FROM coin WHERE uuid = :coinId")
    suspend fun getCoin(coinId: Int): Coin?

    @Query("DELETE FROM coin")
    suspend fun deleteAllCoin()

}