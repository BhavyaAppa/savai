package com.example.alokozapshopapplication.ui.fragment.cartfragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.cartfragment.model.addtocartitem.AddToCartItemResponse
import com.example.alokozapshopapplication.ui.fragment.cartfragment.model.addtocartitem.AddToCartRequestItem
import com.example.alokozapshopapplication.ui.fragment.cartfragment.model.emptycart.EmptyCartRequest
import com.example.alokozapshopapplication.ui.fragment.cartfragment.model.emptycart.EmptyCartResponce
import com.example.alokozapshopapplication.ui.fragment.cartfragment.repository.AddToCartDetailsRepository
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.launch

class AddToCartDetailsViewModel: ViewModelBase() {

    var addToCartRequestItem: AddToCartRequestItem? = null
    var addToCartDetailsResponseLiveData = MutableLiveData<ResponseHandler<AddToCartItemResponse>>()
    var addToCartDetailsRepository: AddToCartDetailsRepository? =
        AddToCartDetailsRepository(ApiClient.getApiInterface())
    init {
        addToCartRequestItem = AddToCartRequestItem()
    }
    fun getAddToCartDetails(customerToken: String,quoteId: String) {
        viewModelScope.launch(coroutineContext) {
            addToCartDetailsResponseLiveData.value = ResponseHandler.Loading
            addToCartDetailsResponseLiveData.value = addToCartDetailsRepository?.getAddToCartDetails(customerToken,quoteId)
        }

    }

    var emptyCartRequest: EmptyCartRequest? = null
    var emptyCartResponseLiveData = MutableLiveData<ResponseHandler<EmptyCartResponce>>()
    init {
        emptyCartRequest = EmptyCartRequest()
    }

    fun getAddToCartEmpty(customerToken:String,quoteId: String) {
        viewModelScope.launch(coroutineContext) {
            emptyCartResponseLiveData.value = ResponseHandler.Loading
            emptyCartResponseLiveData.value = addToCartDetailsRepository?.getAddToCartEmpty(customerToken,quoteId)
        }

    }


    var addWishListResponseLiveData = MutableLiveData<ResponseHandler<AddWishListResponse>>()

    fun addWishList(customerToken: String,productId: String) {
        viewModelScope.launch(coroutineContext) {
            addWishListResponseLiveData.value = ResponseHandler.Loading
            addWishListResponseLiveData.value = addToCartDetailsRepository?.addWishList(customerToken,productId)
        }


    }

    var removeWishListResponseLiveData = MutableLiveData<ResponseHandler<RemoveWishListResponse>>()

    fun removeWishList(customerToken: String,itemId:String) {
        viewModelScope.launch(coroutineContext) {
            removeWishListResponseLiveData.value = ResponseHandler.Loading
            removeWishListResponseLiveData.value = addToCartDetailsRepository?.removeWishList(customerToken,itemId)
        }

    }







/*
    private var addToCartDetailsResponse: MutableLiveData<BaseResponse<AddToCartItemResponse>> = MutableLiveData()
    val addToCartDetailsResponseLiveData: LiveData<BaseResponse<AddToCartItemResponse>>
        get() = addToCartDetailsResponse


    fun getAddToCartDetails(customerToken:String,quoteId: String) {
        viewModelScope.launch() {
            addToCartDetailsResponse.postValue(BaseResponse.Loading())
            try {
                val responce = addToCartDetailsRepository.getAddToCartDetails(customerToken,quoteId)
                if (responce.isSuccessful) {
                    addToCartDetailsResponse.postValue(BaseResponse.Success(responce.body()))
                    Log.d("ResponseSucess", responce.body().toString())

                } else {
                    Log.d("ResponseError", responce.body().toString())
                }
            } catch (e: Exception) {
                Log.d("Exception", e.message.toString())
            }

        }

    }




    private var addToCartEmptyResponse: MutableLiveData<BaseResponse<EmptyCartResponce>> = MutableLiveData()
    val addToCartEmptyResponseLiveData: LiveData<BaseResponse<EmptyCartResponce>>
        get() = addToCartEmptyResponse


    fun getAddToCartEmpty(customerToken:String,quoteId: String) {
        viewModelScope.launch() {
            addToCartEmptyResponse.postValue(BaseResponse.Loading())
            try {
                val responce = addToCartDetailsRepository.getAddToCartEmpty(customerToken,quoteId)
                if (responce.isSuccessful) {
                    addToCartEmptyResponse.postValue(BaseResponse.Success(responce.body()))
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