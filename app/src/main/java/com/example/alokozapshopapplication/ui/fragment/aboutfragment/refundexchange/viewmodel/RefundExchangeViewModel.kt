package com.example.alokozapshopapplication.ui.fragment.aboutfragment.refundexchange.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.refundexchange.model.RefundExchangeResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.refundexchange.repository.RefundExchangeRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.launch



class RefundExchangeViewModel: ViewModelBase() {


    var refundExchangeRepository = RefundExchangeRepository(ApiClient.getApiInterface())
    val refundExchangeResponce =
        MutableLiveData<ResponseHandler<RefundExchangeResponce>>()


    fun getRefundExchange(){
        viewModelScope.launch(coroutineContext) {
            refundExchangeResponce.value = ResponseHandler.Loading
            refundExchangeResponce.value = refundExchangeRepository.getRefundExchange()
        }
    }


}