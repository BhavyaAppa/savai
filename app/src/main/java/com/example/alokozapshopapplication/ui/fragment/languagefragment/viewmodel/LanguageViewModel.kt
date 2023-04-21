package com.example.alokozapshopapplication.ui.fragment.languagefragment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.countryfragment.model.CountryRequest
import com.example.alokozapshopapplication.ui.fragment.countryfragment.model.CountryResponse
import com.example.alokozapshopapplication.ui.fragment.countryfragment.repository.CountryRepository
import com.example.alokozapshopapplication.ui.fragment.languagefragment.model.LanguageResponce
import com.example.alokozapshopapplication.ui.fragment.languagefragment.repository.LanguageRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.BaseResponse
import kotlinx.coroutines.launch

class LanguageViewModel: ViewModelBase()  {

    var observableFieldlanguage: String? = null
    var languageRepositoryResponseLiveData = MutableLiveData<ResponseHandler<LanguageResponce>>()
    var languageRepository: LanguageRepository? =
        LanguageRepository(ApiClient.getApiInterface())

    fun getLanguageListing(countryId: String,
                          websiteId: String) {
        viewModelScope.launch(coroutineContext) {
            languageRepositoryResponseLiveData.value = ResponseHandler.Loading
            languageRepositoryResponseLiveData.value = languageRepository?.getLanguageListing(countryId, websiteId)
        }

    }


}