package com.example.alokozapshopapplication.ui.fragment.myaccountfragment.repository.profileviewrepository


import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.model.deleteaccountmodel.DeleteAccountResponce
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.model.profiledata.ProfileResponse
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface

import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



class ProfileRepository( val apiInterface: ApiInterface):BaseRepository() {

    suspend fun getProfileData(customerToken:String): ResponseHandler<ProfileResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getProfileData(customerToken=customerToken)
                })
        }
    }

    suspend fun getDeleteAccount(customerToken:String): ResponseHandler<DeleteAccountResponce> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getDeleteAccount(customerToken=customerToken)
                })
        }
    }

}