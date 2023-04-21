package com.example.alokozapshopapplication.ui.fragment.aboutfragment.privacystatement.repository

import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.privacystatement.model.PrivacyStatementResponce
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



class PrivacyStatementRepository(val apiInterface: ApiInterface) : BaseRepository() {

    suspend fun getPrivacyStatement(): ResponseHandler<PrivacyStatementResponce> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getPrivacyStatement()
                })
        }
    }
}