package com.example.coinproject.service

import com.example.coinproject.model.Coin
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //Base URL -> https://api.coingecko.com/api/v3/
    //EndPoint -> /coins/markets
    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 200,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false
    ): List<Coin>

}