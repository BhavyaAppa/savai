package com.example.alokozapshopapplication.ui.fragment.referfragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.referfragment.model.ReferResponse
import com.example.alokozapshopapplication.ui.fragment.referfragment.repository.RefarRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.BaseResponse
import kotlinx.coroutines.launch

class RefarViewModel: ViewModelBase() {

    var refarRepository = RefarRepository(ApiClient.getApiInterface())
    val referResponseLiveData =
        MutableLiveData<ResponseHandler<ReferResponse>>()


    fun referFriend(customerToken:String,quoteId: String){
        viewModelScope.launch(coroutineContext) {
            referResponseLiveData.value = ResponseHandler.Loading
            referResponseLiveData.value = refarRepository.referFriend(customerToken,quoteId)
        }
    }






}