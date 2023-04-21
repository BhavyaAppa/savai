package com.example.alokozapshopapplication.ui.fragment.aboutfragment.termscondition.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.termscondition.model.TermsConditionResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.termscondition.repository.TermsConditionRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.launch


class TermsConditionViewModel: ViewModelBase()  {


    var termsConditionRepository = TermsConditionRepository(ApiClient.getApiInterface())
    val termsConditionResponce =
        MutableLiveData<ResponseHandler<TermsConditionResponce>>()

    fun getTermsCondition() {
        viewModelScope.launch(coroutineContext) {
            termsConditionResponce.value = ResponseHandler.Loading
            termsConditionResponce.value = termsConditionRepository.getTermsCondition()
        }
    }

}


