package com.example.alokozapshopapplication.ui.fragment.rewardhistoryfragment.repository


import com.example.alokozapshopapplication.ui.base.BaseRepository

import com.example.alokozapshopapplication.ui.fragment.rewardhistoryfragment.model.RewardHistoryResponse
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



class RewardHistoryRepository(val apiInterface: ApiInterface) : BaseRepository()  {

    suspend fun rewardHistory(customerToken:String): ResponseHandler<RewardHistoryResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.rewardHistory(customerToken = customerToken)
                })
        }
    }

}


