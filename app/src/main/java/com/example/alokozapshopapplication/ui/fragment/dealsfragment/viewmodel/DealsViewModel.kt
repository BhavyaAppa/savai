package com.example.alokozapshopapplication.ui.fragment.dealsfragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.dealsfragment.model.deals.DealsRequest
import com.example.alokozapshopapplication.ui.fragment.dealsfragment.model.deals.DealsResponse
import com.example.alokozapshopapplication.ui.fragment.dealsfragment.repository.DealsRepository
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartRequest
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.removecart.RemoveCartRequest
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.removecart.RemoveCartResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.launch

class DealsViewModel : ViewModelBase()  {


    var dealsResponseLiveData = MutableLiveData<ResponseHandler<DealsResponse>>()
    var dealsRepository: DealsRepository? =
        DealsRepository(ApiClient.getApiInterface())



    fun getDealsData(customerToken: String ) {
        viewModelScope.launch(coroutineContext) {
            dealsResponseLiveData.value = ResponseHandler.Loading
            dealsResponseLiveData.value = dealsRepository?.getDealsData(customerToken)
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
            addToCartResponseLiveData.value = dealsRepository?.getAddToCart(customerToken,productId,quoteId)
        }

    }

    var removeCartRequest: RemoveCartRequest? = null
    var removeCartResponseLiveData = MutableLiveData<ResponseHandler<RemoveCartResponse>>()

    init {
        removeCartRequest = RemoveCartRequest()
    }

    fun removeCart() {
        viewModelScope.launch(coroutineContext) {
            removeCartResponseLiveData.value = ResponseHandler.Loading
            removeCartResponseLiveData.value = dealsRepository?.removeCart(removeCartRequest!!)
        }

    }

    var addWishListResponseLiveData = MutableLiveData<ResponseHandler<AddWishListResponse>>()

    fun addWishList(customerToken: String,productId: String) {
        viewModelScope.launch(coroutineContext) {
            addWishListResponseLiveData.value = ResponseHandler.Loading
            addWishListResponseLiveData.value = dealsRepository?.addWishList(customerToken,productId)
        }

    }

    var removeWishListResponseLiveData = MutableLiveData<ResponseHandler<RemoveWishListResponse>>()

    fun removeWishList(customerToken: String,itemId:String) {
        viewModelScope.launch(coroutineContext) {
            removeWishListResponseLiveData.value = ResponseHandler.Loading
            removeWishListResponseLiveData.value = dealsRepository?.removeWishList(customerToken,itemId)
        }

    }
}
