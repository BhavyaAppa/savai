package com.example.alokozapshopapplication.ui.fragment.aboutfragment.termscondition.repository

import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.termscondition.model.TermsConditionResponce
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



class TermsConditionRepository(val apiInterface: ApiInterface) : BaseRepository() {
    suspend fun getTermsCondition(): ResponseHandler<TermsConditionResponce> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getTermsCondition()
                })
        }
    }
}



