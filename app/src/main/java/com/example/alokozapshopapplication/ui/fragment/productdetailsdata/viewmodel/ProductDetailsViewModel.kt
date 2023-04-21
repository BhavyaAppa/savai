package com.example.alokozapshopapplication.ui.fragment.productdetailsdata.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartRequest
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.model.ProductDetailsResponse
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.repository.ProductDetailsRepository
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler

import kotlinx.coroutines.launch

class ProductDetailsViewModel: ViewModelBase() {


    var productDetailsResponseLiveData = MutableLiveData<ResponseHandler<ProductDetailsResponse>>()
    var productDetailsRepository: ProductDetailsRepository? =
        ProductDetailsRepository(ApiClient.getApiInterface())

    fun productPageData(customerToken: String,quoteId: String,productId: String  ) {
        viewModelScope.launch(coroutineContext) {
            productDetailsResponseLiveData.value = ResponseHandler.Loading
            productDetailsResponseLiveData.value = productDetailsRepository?.productPageData(customerToken,quoteId,productId,)
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
            addToCartResponseLiveData.value = productDetailsRepository?.getAddToCart(customerToken,productId,quoteId)
        }

    }

    var addWishListResponseLiveData = MutableLiveData<ResponseHandler<AddWishListResponse>>()

    fun addWishList(customerToken: String,productId: String) {
        viewModelScope.launch(coroutineContext) {
            addWishListResponseLiveData.value = ResponseHandler.Loading
            addWishListResponseLiveData.value = productDetailsRepository?.addWishList(customerToken,productId)
        }


    }

    var removeWishListResponseLiveData = MutableLiveData<ResponseHandler<RemoveWishListResponse>>()

    fun removeWishList(customerToken: String,itemId:String) {
        viewModelScope.launch(coroutineContext) {
            removeWishListResponseLiveData.value = ResponseHandler.Loading
            removeWishListResponseLiveData.value = productDetailsRepository?.removeWishList(customerToken,itemId)
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
