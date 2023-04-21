package com.example.alokozapshopapplication.ui.fragment.searchfragment.viewmodel


import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.searchfragment.model.searchmodel.SearchDataRespoance
import com.example.alokozapshopapplication.ui.fragment.searchfragment.repository.SearchRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.launch


class SaerchViewModel : ViewModelBase()  {

    var searchResponseLiveData = MutableLiveData<ResponseHandler<HomeResponse>>()
    var searchRepository: SearchRepository? =
        SearchRepository(ApiClient.getApiInterface())

    fun searchCategory(customerToken: String) {
        viewModelScope.launch(coroutineContext) {
            searchResponseLiveData.value = ResponseHandler.Loading
            searchResponseLiveData.value = searchRepository?.searchCategory(customerToken)
        }

    }


    var searchItemResponceLiveData = MutableLiveData<ResponseHandler<SearchDataRespoance>>()
    fun searchItem(q: String ) {
        viewModelScope.launch(coroutineContext) {
            searchItemResponceLiveData.value = ResponseHandler.Loading
            searchItemResponceLiveData.value = searchRepository?.searchItem(q)
        }

    }


}