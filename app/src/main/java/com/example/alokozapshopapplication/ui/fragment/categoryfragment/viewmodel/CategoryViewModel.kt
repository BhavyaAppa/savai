package com.example.alokozapshopapplication.ui.fragment.categoryfragment.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.model.CategoryItemResponse
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.repository.CategoryRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.launch

class CategoryViewModel: ViewModelBase()  {

    var categoryItemResponseLiveData = MutableLiveData<ResponseHandler<CategoryItemResponse>>()
    var categoryRepository: CategoryRepository? =
        CategoryRepository(ApiClient.getApiInterface())

    fun getCategoryItem() {
        viewModelScope.launch(coroutineContext) {
            categoryItemResponseLiveData.value = ResponseHandler.Loading
            categoryItemResponseLiveData.value = categoryRepository?.getCategoryItem()
        }

    }

/*
    private var categoryItemResponse: MutableLiveData<BaseResponse<CategoryItemResponse>> = MutableLiveData()
    val categoryItemResponseLiveData: LiveData<BaseResponse<CategoryItemResponse>>
        get() = categoryItemResponse


    fun getCategoryItem() {
        viewModelScope.launch() {
            categoryItemResponse.postValue(BaseResponse.Loading())

            try {
                val responce = categoryRepository.getCategoryItem()
                if (responce.isSuccessful) {
                    categoryItemResponse.postValue(BaseResponse.Success(responce.body()))
                    Log.d("ResponseSucess", responce.body().toString())
                } else {
                    Log.d("ResponseError", responce.body().toString())
                }
            } catch (e: Exception) {
                Log.d("NewsMedia Exception", e.message.toString())
            }


        }

    }
*/


}