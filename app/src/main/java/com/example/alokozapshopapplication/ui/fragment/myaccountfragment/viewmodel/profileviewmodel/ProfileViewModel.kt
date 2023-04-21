package com.example.alokozapshopapplication.ui.fragment.myaccountfragment.viewmodel.profileviewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.newsmedia.model.NewsMediaResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.newsmedia.repository.NewsMediaRepository
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.model.deleteaccountmodel.DeleteAccountResponce
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.model.profiledata.ProfileResponse
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.repository.profileviewrepository.ProfileRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.BaseResponse
import kotlinx.coroutines.launch

class ProfileViewModel:ViewModelBase() {


    var profileRepository = ProfileRepository(ApiClient.getApiInterface())
    val profileResponceData =
        MutableLiveData<ResponseHandler<ProfileResponse>>()


    fun getProfileData(customerToken:String){
        viewModelScope.launch(coroutineContext) {
            profileResponceData.value = ResponseHandler.Loading
            profileResponceData.value = profileRepository.getProfileData(customerToken)
        }
    }



    val deleteAccountResponceData =
        MutableLiveData<ResponseHandler<DeleteAccountResponce>>()


    fun getDeleteAccount(customerToken:String){
        viewModelScope.launch(coroutineContext) {
            deleteAccountResponceData.value = ResponseHandler.Loading
            deleteAccountResponceData.value = profileRepository.getDeleteAccount(customerToken)
        }
    }

}