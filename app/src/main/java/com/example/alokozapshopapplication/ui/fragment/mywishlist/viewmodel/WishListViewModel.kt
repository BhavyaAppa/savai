package com.example.alokozapshopapplication.ui.fragment.mywishlist.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartRequest
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.getwishlist.WishListResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.repository.WishListRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler

import kotlinx.coroutines.launch

class WishListViewModel: ViewModelBase() {

    var wishListResponse: MutableLiveData<WishListResponse>? = MutableLiveData()
    var wishListResponseLiveData = MutableLiveData<ResponseHandler<WishListResponse>>()
    var wishListRepository: WishListRepository? =
        WishListRepository(ApiClient.getApiInterface())

    fun wishList(customerToken: String,pageNumber: Int) {
        viewModelScope.launch(coroutineContext) {
            wishListResponseLiveData.value = ResponseHandler.Loading
            wishListResponseLiveData.value = wishListRepository?.wishList(customerToken,pageNumber)
        }

    }

    var removeWishListResponseLiveData = MutableLiveData<ResponseHandler<RemoveWishListResponse>>()

    fun removeWishList(customerToken: String,itemId:String) {
        viewModelScope.launch(coroutineContext) {
            removeWishListResponseLiveData.value = ResponseHandler.Loading
            removeWishListResponseLiveData.value = wishListRepository?.removeWishList(customerToken,itemId)
        }

    }

    var addToCartRequest: AddToCartRequest? = null
    var addToCartResponseLiveData = MutableLiveData<ResponseHandler<AddToCartResponse>>()

    init {
        addToCartRequest = AddToCartRequest()
    }


    fun getAddToCart(customerToken: String,productId: String,quoteId: String) {
        viewModelScope.launch(coroutineContext) {
            addToCartResponseLiveData.value = ResponseHandler.Loading
            addToCartResponseLiveData.value = wishListRepository?.getAddToCart(customerToken,productId,quoteId)
        }

    }












/*
    private var productDetailsResponse: MutableLiveData<BaseResponse<ProductDetailsResponse>> = MutableLiveData()
    val productDetailsResponseLiveData: LiveData<BaseResponse<ProductDetailsResponse>>
        get() = productDetailsResponse


    fun productPageData(customerToken: String,quoteId: String, productId: String ) {
        viewModelScope.launch() {
            productDetailsResponse.postValue(BaseResponse.Loading())
            try {
                val responce = productDetailsRepository.productPageData(customerToken,quoteId,productId)
                if (responce.isSuccessful) {
                    productDetailsResponse.postValue(BaseResponse.Success(responce.body()))
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
