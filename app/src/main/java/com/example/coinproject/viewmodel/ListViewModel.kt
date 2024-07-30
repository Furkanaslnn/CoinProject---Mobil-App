package com.example.coinproject.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.coinproject.model.Coin
import com.example.coinproject.roomdb.CoinDataBase
import com.example.coinproject.service.RetrofitInstance
import com.example.coinproject.util.PrivateSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val coins = MutableLiveData<List<Coin>>()
    val coinErrorMessage = MutableLiveData<Boolean>()
    val coinLoading = MutableLiveData<Boolean>()

    private val conApiService = RetrofitInstance()
    private val privateSharedPreferences = PrivateSharedPreferences(getApplication())

    private val updateTime = 1 * 60 * 1000 * 1000 * 1000L

    fun refreshData() {
        val saveTime = privateSharedPreferences.getTime(0)

        if (saveTime != null && saveTime != 0L && System.nanoTime() - saveTime < updateTime) {
            getDataFromRoom()
        } else {
            getDataFromInternet()
        }
    }

    fun refreshFromInternet() {
        getDataFromInternet()
    }

    private fun getDataFromRoom() {
        coinLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val coinList = CoinDataBase(getApplication()).coinDao().getAllCoin()
            withContext(Dispatchers.Main) {
                showCoins(coinList)
                Toast.makeText(getApplication(), "We got the data from the Room", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun getDataFromInternet() {
        coinLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val coinList = conApiService.getData()
            withContext(Dispatchers.Main) {
                coinLoading.value = false
                saveToRoom(coinList)
                Toast.makeText(
                    getApplication(),
                    "We got the data from the internet",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun showCoins(coinList: List<Coin>) {
        coins.value = coinList
        coinErrorMessage.value = false
        coinLoading.value = false
    }

    private fun saveToRoom(coinList: List<Coin>) {

        viewModelScope.launch {

            val dao = CoinDataBase(getApplication()).coinDao()
            dao.deleteAllCoin()
            val uuidList = dao.insertAll(*coinList.toTypedArray())
            var i = 0
            while (i < coinList.size) {
                coinList[i].uuid = uuidList[i].toInt()
                i++
            }
            showCoins(coinList)

        }
        privateSharedPreferences.saveTime(System.nanoTime())
    }

}