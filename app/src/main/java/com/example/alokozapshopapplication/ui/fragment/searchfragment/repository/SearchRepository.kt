package com.example.alokozapshopapplication.ui.fragment.searchfragment.repository


import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.searchfragment.model.searchmodel.SearchDataRespoance
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRepository(val apiInterface: ApiInterface) : BaseRepository() {


    suspend fun searchCategory(customerToken: String): ResponseHandler<HomeResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.searchCategory(customerToken=customerToken)
                })
        }
    }

    suspend fun searchItem(q: String): ResponseHandler<SearchDataRespoance> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.searchItem(q=q)
                })
        }
    }

}