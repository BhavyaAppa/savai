package com.example.alokozapshopapplication.ui.fragment.languagefragment.repository

import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.languagefragment.model.LanguageResponce
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LanguageRepository(val apiInterface: ApiInterface) : BaseRepository() {


    suspend fun getLanguageListing(
        countryId: String,
        websiteId: String
    ): ResponseHandler<LanguageResponce> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getLanguageListing(
                        countryId = countryId,
                        websiteId = websiteId
                    )
                })
        }
    }

}