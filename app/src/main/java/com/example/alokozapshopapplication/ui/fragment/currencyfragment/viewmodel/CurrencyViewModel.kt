package com.example.alokozapshopapplication.ui.fragment.currencyfragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.currencyfragment.model.CurrencyResponse
import com.example.alokozapshopapplication.ui.fragment.currencyfragment.repository.CurrencyRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler


import kotlinx.coroutines.launch

class CurrencyViewModel: ViewModelBase()  {


    var currencyRepositoryResponseLiveData = MutableLiveData<ResponseHandler<CurrencyResponse>>()
    var currencyRepository: CurrencyRepository? =
        CurrencyRepository(ApiClient.getApiInterface())

    fun getCurrencyListing() {
        viewModelScope.launch(coroutineContext) {
            currencyRepositoryResponseLiveData.value = ResponseHandler.Loading
            currencyRepositoryResponseLiveData.value = currencyRepository?.getCurrencyListing()
        }

    }



}