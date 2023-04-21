package com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.timeslotviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.timeslotmodel.TimeSlotResponse
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.deliveryinfomodel.DeliveryInfoResponce
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.timeslotrepository.DeliveryInfoRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.launch

class DeliveryInfoViewModel : ViewModelBase() {

    var deliveryInfoResponseLiveData = MutableLiveData<ResponseHandler<DeliveryInfoResponce>>()
    var deliveryInfoRepository: DeliveryInfoRepository? =
        DeliveryInfoRepository(ApiClient.getApiInterface())

    fun getTimeDateSlot(quoteId: String, slotId: String) {
        viewModelScope.launch(coroutineContext) {
            deliveryInfoResponseLiveData.value = ResponseHandler.Loading
            deliveryInfoResponseLiveData.value =
                deliveryInfoRepository?.getTimeDateSlot(quoteId, slotId)
        }

    }


    var timeSlotResponseLiveData = MutableLiveData<ResponseHandler<TimeSlotResponse>>()
    var selected: Boolean = false
    var fisrtSelected: Boolean = true

    fun getTimeSlot() {
        viewModelScope.launch(coroutineContext) {
            timeSlotResponseLiveData.value = ResponseHandler.Loading
            timeSlotResponseLiveData.value = deliveryInfoRepository?.getTimeSlot()
        }

    }


}