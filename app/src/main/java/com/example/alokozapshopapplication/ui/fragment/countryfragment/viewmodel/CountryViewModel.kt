package com.example.alokozapshopapplication.ui.fragment.countryfragment.viewmodel


import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.countryfragment.model.CountryResponse
import com.example.alokozapshopapplication.ui.fragment.countryfragment.repository.CountryRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.launch

class CountryViewModel : ViewModelBase() {

    var countryRepositoryResponseLiveData = MutableLiveData<ResponseHandler<CountryResponse>>()
    var countryRepository: CountryRepository? =
        CountryRepository(ApiClient.getApiInterface())

    fun getCountryListing() {
        viewModelScope.launch(coroutineContext) {
            countryRepositoryResponseLiveData.value = ResponseHandler.Loading
            countryRepositoryResponseLiveData.value = countryRepository?.getCountryListing()
        }

    }

}