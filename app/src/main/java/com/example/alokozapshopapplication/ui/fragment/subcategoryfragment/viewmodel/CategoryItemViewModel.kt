package com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeRequest
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartRequest
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homerepository.HomeRepository
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.CategoryItemListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.repository.CategoryItemListRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler

import com.example.alokozapshopapplication.ui.utils.BaseResponse
import kotlinx.coroutines.launch

class CategoryItemViewModel: ViewModelBase()   {

    var categoryItemListResponseLiveData = MutableLiveData<ResponseHandler<CategoryItemListResponse>>()

    var categoryItemListResponse: MutableLiveData<CategoryItemListResponse>? = MutableLiveData()

    var categoryItemListRepository: CategoryItemListRepository? =
        CategoryItemListRepository(ApiClient.getApiInterface())

    fun getCategoryItemList(customerToken: String,type:String ,id:String, pageNumber: Int) {
        viewModelScope.launch(coroutineContext) {
            categoryItemListResponseLiveData.value = ResponseHandler.Loading
            categoryItemListResponseLiveData.value = categoryItemListRepository?.getCategoryItemList(customerToken,type, id,pageNumber)
        }

    }


    var addToCartResponseLiveData = MutableLiveData<ResponseHandler<AddToCartResponse>>()

    fun getAddToCart(customerToken: String,productId: String,quoteId: String) {
        viewModelScope.launch(coroutineContext) {
            addToCartResponseLiveData.value = ResponseHandler.Loading
            addToCartResponseLiveData.value = categoryItemListRepository?.getAddToCart(customerToken,productId,quoteId)
        }

    }

    var addWishListResponseLiveData = MutableLiveData<ResponseHandler<AddWishListResponse>>()

    fun addWishList(customerToken: String,productId: String) {
        viewModelScope.launch(coroutineContext) {
            addWishListResponseLiveData.value = ResponseHandler.Loading
            addWishListResponseLiveData.value = categoryItemListRepository?.addWishList(customerToken,productId)
        }

    }







/*

    private var categoryItemListResponse: MutableLiveData<BaseResponse<CategoryItemListResponse>> = MutableLiveData()
    val categoryItemListResponseLiveData: LiveData<BaseResponse<CategoryItemListResponse>>
        get() = categoryItemListResponse


    fun getCategoryListItem(type:String,id:String) {
        viewModelScope.launch {
            categoryItemListResponse.postValue(BaseResponse.Loading())

            try {
                val responce = categoryItemListRepository.getCategoryItemList(type,id)
                if (responce.isSuccessful) {
                    categoryItemListResponse.postValue(BaseResponse.Success(responce.body()))
                    Log.d("ResponseSucess AAA", responce.body().toString())
                } else {
                    Log.d("ResponseError AAA", responce.body().toString())
                }
            } catch (e: Exception) {
                Log.d("NewsMedia Exception AAA", e.message.toString())
            }
        }

    }






    private var addToCartResponse: MutableLiveData<BaseResponse<AddToCartResponse>> = MutableLiveData()
    val addToCartResponseLiveData: LiveData<BaseResponse<AddToCartResponse>>
        get() = addToCartResponse


    fun getAddToCart(customerToken:String,productId: String,quoteId: String) {
        viewModelScope.launch() {
            addToCartResponse.postValue(BaseResponse.Loading())
            try {
                val responce = categoryItemListRepository.getAddToCart(customerToken,productId,quoteId)
                if (responce.isSuccessful) {
                    addToCartResponse.postValue(BaseResponse.Success(responce.body()))
                    Log.d("ResponseSucess", responce.body().toString())

                } else {
                    Log.d("ResponseError", responce.body().toString())
                }
            } catch (e: Exception) {
                Log.d("Exception", e.message.toString())
            }

        }

    }

*/


}