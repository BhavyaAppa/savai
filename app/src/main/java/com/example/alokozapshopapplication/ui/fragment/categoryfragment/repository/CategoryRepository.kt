package com.example.alokozapshopapplication.ui.fragment.categoryfragment.repository


import com.example.alokozapshopapplication.ui.base.BaseRepository

import com.example.alokozapshopapplication.ui.fragment.categoryfragment.model.CategoryItemResponse

import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CategoryRepository(val apiInterface: ApiInterface) : BaseRepository() {




    suspend fun getCategoryItem(): ResponseHandler<CategoryItemResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getCategoryItem()
                })
        }
    }

    /*    suspend fun getCategoryItem(): Response<CategoryItemResponse> {
        return ApiRetrofitObject.api.getCategoryItem()
    }*/


}