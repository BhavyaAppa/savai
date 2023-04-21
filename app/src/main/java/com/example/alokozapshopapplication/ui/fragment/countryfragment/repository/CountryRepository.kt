package com.example.alokozapshopapplication.ui.fragment.countryfragment.repository


import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.countryfragment.model.CountryResponse
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryRepository (val apiInterface: ApiInterface) : BaseRepository(){

    suspend fun getCountryListing(): ResponseHandler<CountryResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getCountryListing()
                })
        }
    }
}