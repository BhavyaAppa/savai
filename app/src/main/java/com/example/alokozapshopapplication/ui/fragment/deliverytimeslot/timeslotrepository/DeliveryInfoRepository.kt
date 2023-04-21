package com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.timeslotrepository

import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.deliveryinfomodel.DeliveryInfoResponce
import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.timeslotmodel.TimeSlotResponse
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeliveryInfoRepository(val apiInterface: ApiInterface) : BaseRepository(){


    suspend fun getTimeDateSlot(quoteId: String,slotId: String): ResponseHandler<DeliveryInfoResponce> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getTimeDateSlot(quoteId=quoteId,slotId=slotId)
                })

        }
    }


    suspend fun getTimeSlot(): ResponseHandler<TimeSlotResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getTimeSlot()
                })

        }
    }


}