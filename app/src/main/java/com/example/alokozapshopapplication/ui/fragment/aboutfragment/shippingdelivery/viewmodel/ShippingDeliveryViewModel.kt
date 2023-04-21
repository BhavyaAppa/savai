package com.example.alokozapshopapplication.ui.fragment.aboutfragment.shippingdelivery.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.shippingdelivery.model.ShippingDeliveryResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.shippingdelivery.repository.ShippingDeliveryRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler

import kotlinx.coroutines.launch


class ShippingDeliveryViewModel:ViewModelBase() {

    var shippingDeliveryRepository = ShippingDeliveryRepository(ApiClient.getApiInterface())
    val shippingDeliveryResponce =
        MutableLiveData<ResponseHandler<ShippingDeliveryResponce>>()

    fun getShippingDelivery(){
        viewModelScope.launch(coroutineContext) {
            shippingDeliveryResponce.value = ResponseHandler.Loading
            shippingDeliveryResponce.value = shippingDeliveryRepository.getShippingDelivery()
        }
    }


}