package com.example.alokozapshopapplication.ui.fragment.aboutfragment.shippingdelivery.repository

import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.shippingdelivery.model.ShippingDeliveryResponce
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ShippingDeliveryRepository(val apiInterface: ApiInterface) : BaseRepository()  {


    suspend fun getShippingDelivery(): ResponseHandler<ShippingDeliveryResponce> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getShippingDelivery()
                })
        }
    }
}