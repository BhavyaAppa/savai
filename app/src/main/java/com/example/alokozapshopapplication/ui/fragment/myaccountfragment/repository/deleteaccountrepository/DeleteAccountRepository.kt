package com.example.alokozapshopapplication.ui.fragment.myaccountfragment.repository.deleteaccountrepository

import com.example.alokozapshopapplication.ui.api.ApiRetrofitObject
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.model.deleteaccountmodel.DeleteAccountResponce
import retrofit2.Response

class DeleteAccountRepository {

    suspend fun getDeleteAccount(customerToken:String): Response<DeleteAccountResponce> {
        return ApiRetrofitObject.api.getDeleteAccount(customerToken=customerToken)
    }
}