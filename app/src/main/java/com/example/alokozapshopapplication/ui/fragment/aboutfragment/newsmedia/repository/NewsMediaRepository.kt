package com.example.alokozapshopapplication.ui.fragment.aboutfragment.newsmedia.repository

import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.newsmedia.model.NewsMediaResponce
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class NewsMediaRepository(val apiInterface: ApiInterface) : BaseRepository()  {


    suspend fun getNewsMedia(): ResponseHandler<NewsMediaResponce> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getNewsMedia()
                })
        }
    }


}