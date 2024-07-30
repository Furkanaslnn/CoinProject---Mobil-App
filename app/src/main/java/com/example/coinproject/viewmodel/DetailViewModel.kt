package com.example.coinproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.coinproject.model.Coin
import com.example.coinproject.roomdb.CoinDataBase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    val coinLiveData = MutableLiveData<Coin?>()

    fun getRoomData(uuid: Int) {
        viewModelScope.launch {
            val dao = CoinDataBase(getApplication()).coinDao()
            val coin = dao.getCoin(uuid)
            coinLiveData.value = coin
        }
    }
}