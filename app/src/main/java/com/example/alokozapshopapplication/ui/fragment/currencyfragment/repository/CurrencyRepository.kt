package com.example.alokozapshopapplication.ui.fragment.currencyfragment.repository

import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.currencyfragment.model.CurrencyResponse
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import retrofit2.Response

class CurrencyRepository(val apiInterface: ApiInterface) : BaseRepository() {

    suspend fun getCurrencyListing(
    ): ResponseHandler<CurrencyResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getCurrencyListing(
                    )
                })
        }
    }

}