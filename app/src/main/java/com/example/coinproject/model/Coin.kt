package com.example.coinproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Coin(
    @ColumnInfo(name = "id") val id: String?,

    @ColumnInfo(name = "symbol") val symbol: String?,

    @ColumnInfo(name = "name") val name: String?,

    @ColumnInfo(name = "image") val image: String?,

    @ColumnInfo(name = "current_price") val currentPrice: Double?,

    @ColumnInfo(name = "market_cap") val marketCap: Double?,

    @ColumnInfo(name = "market_cap_rank") val marketCapRank: Long?,

    @ColumnInfo(name = "fully_diluted_valuation") val fullyDilutedValuation: Double?,

    @ColumnInfo(name = "total_volume") val totalVolume: Double?,

    @ColumnInfo(name = "high_24h") val high24H: Double?,

    @ColumnInfo(name = "low_24h") val low24H: Double?,

    @ColumnInfo(name = "price_change_24h") val priceChange24H: Double?,

    @ColumnInfo(name = "price_change_percentage_24h") val priceChangePercentage24H: Double?,

    @ColumnInfo(name = "market_cap_change_24h") val marketCapChange24H: Double?,

    @ColumnInfo(name = "market_cap_change_percentage_24h") val marketCapChangePercentage24H: Double?,

    @ColumnInfo(name = "circulating_supply") val circulatingSupply: Double?,

    @ColumnInfo(name = "total_supply") val totalSupply: Double?,

    @ColumnInfo(name = "max_supply") val maxSupply: Double?,

    @ColumnInfo(name = "ath") val ath: Double?,

    @ColumnInfo(name = "ath_change_percentage") val athChangePercentage: Double?,

    @ColumnInfo(name = "ath_date") val athDate: String?,

    @ColumnInfo(name = "atl") val atl: Double?,

    @ColumnInfo(name = "atl_change_percentage") val atlChangePercentage: Double?,

    @ColumnInfo(name = "atl_date") val atlDate: String?,

    @ColumnInfo(name = "last_updated") val lastUpdated: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}
