package com.example.alokozapshopapplication.ui.fragment.myaccountfragment.viewmodel.deleteaccountviewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.model.deleteaccountmodel.DeleteAccountResponce
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.repository.deleteaccountrepository.DeleteAccountRepository
import com.example.alokozapshopapplication.ui.utils.BaseResponse
import kotlinx.coroutines.launch

class DeleteAccountViewModel(private  val deleteAccountRepository:DeleteAccountRepository): ViewModel() {


    private var deleteAccountResponce: MutableLiveData<BaseResponse<DeleteAccountResponce>> = MutableLiveData()
    val deleteAccountResponceLiveData: LiveData<BaseResponse<DeleteAccountResponce>>
        get() = deleteAccountResponce


    fun deleteAccount(customerToken:String) {
        viewModelScope.launch {
            deleteAccountResponce.postValue(BaseResponse.Loading())

            try {
                val responce =deleteAccountRepository.getDeleteAccount(customerToken)
                if (responce.isSuccessful) {
                    deleteAccountResponce.postValue(BaseResponse.Success(responce.body()))
                    Log.d("ResponseSuccess ", responce.body().toString())
                } else {
                    Log.d("ResponseError ", responce.body().toString())
                }
            } catch (e: Exception) {
                Log.d(" Exception ", e.message.toString())
            }
        }

    }



}