package com.example.alokozapshopapplication.ui.fragment.aboutfragment.refundexchange.repository


import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.refundexchange.model.RefundExchangeResponce
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RefundExchangeRepository(val apiInterface: ApiInterface) : BaseRepository() {


    suspend fun getRefundExchange(): ResponseHandler<RefundExchangeResponce> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getRefundExchange()
                })
        }
    }
}