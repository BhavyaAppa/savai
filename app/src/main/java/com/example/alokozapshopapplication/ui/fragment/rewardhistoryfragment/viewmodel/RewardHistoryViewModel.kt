package com.example.alokozapshopapplication.ui.fragment.rewardhistoryfragment.viewmodel


import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase

import com.example.alokozapshopapplication.ui.fragment.rewardhistoryfragment.model.RewardHistoryResponse
import com.example.alokozapshopapplication.ui.fragment.rewardhistoryfragment.repository.RewardHistoryRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.launch




class RewardHistoryViewModel: ViewModelBase() {


    var rewardHistoryRepository = RewardHistoryRepository(ApiClient.getApiInterface())
    val rewardHistoryResponceData =
        MutableLiveData<ResponseHandler<RewardHistoryResponse>>()


    fun rewardHistory(customerToken:String) {
        viewModelScope.launch(coroutineContext) {
            rewardHistoryResponceData.value = ResponseHandler.Loading
            rewardHistoryResponceData.value = rewardHistoryRepository.rewardHistory(customerToken)
        }
    }
}








