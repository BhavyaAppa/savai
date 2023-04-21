package com.example.alokozapshopapplication.ui.fragment.aboutfragment.privacystatement.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.privacystatement.model.PrivacyStatementResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.privacystatement.repository.PrivacyStatementRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.launch



class PrivacyStatementViewModel: ViewModelBase() {


    var privacyStatementRepository = PrivacyStatementRepository(ApiClient.getApiInterface())
    val privacyStatementResponceLiveData =
        MutableLiveData<ResponseHandler<PrivacyStatementResponce>>()


    fun getPrivacyStatement() {
        viewModelScope.launch(coroutineContext) {
            privacyStatementResponceLiveData.value = ResponseHandler.Loading
            privacyStatementResponceLiveData.value =
                privacyStatementRepository.getPrivacyStatement()
        }
    }


}