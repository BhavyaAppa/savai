package com.example.alokozapshopapplication.ui.fragment.aboutfragment.newsmedia.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.newsmedia.model.NewsMediaResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.newsmedia.repository.NewsMediaRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler


import kotlinx.coroutines.launch


class NewsMediaViewModel: ViewModelBase() {


    var newsMediaRepository = NewsMediaRepository(ApiClient.getApiInterface())
    val newsMediaResponceData =
        MutableLiveData<ResponseHandler<NewsMediaResponce>>()


    fun getNewsMedia() {
        viewModelScope.launch(coroutineContext) {
            newsMediaResponceData.value = ResponseHandler.Loading
            newsMediaResponceData.value = newsMediaRepository.getNewsMedia()
        }
    }
}