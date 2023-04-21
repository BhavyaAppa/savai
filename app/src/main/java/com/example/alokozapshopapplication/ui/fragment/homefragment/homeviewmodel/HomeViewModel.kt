package com.example.alokozapshopapplication.ui.fragment.homefragment.homeviewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeRequest
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartRequest
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.removecart.RemoveCartRequest
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.removecart.RemoveCartResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homerepository.HomeRepository
import com.example.alokozapshopapplication.ui.fragment.loginfragment.loginmodelclass.LoginRequest
import com.example.alokozapshopapplication.ui.fragment.loginfragment.loginmodelclass.LoginResponse
import com.example.alokozapshopapplication.ui.fragment.loginfragment.loginrepositoryrepo.LoginRepository
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.BaseResponse
import com.example.alokozapshopapplication.ui.utils.Validation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class HomeViewModel: ViewModelBase()  {


    var alreadyDeleted: Boolean = false


    var homeRequestData: HomeRequest? = null
    var homeResponseLiveData = MutableLiveData<ResponseHandler<HomeResponse>>()
    var homeRepository: HomeRepository? =
        HomeRepository(ApiClient.getApiInterface())

    init {
        homeRequestData = HomeRequest()
    }


    fun getHomeData(customerToken: String ) {
        viewModelScope.launch(coroutineContext) {
            homeResponseLiveData.value = ResponseHandler.Loading
            homeResponseLiveData.value = homeRepository?.getHomeData(customerToken)
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
            addToCartResponseLiveData.value = homeRepository?.getAddToCart(customerToken,productId,quoteId)
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
            removeCartResponseLiveData.value = homeRepository?.removeCart(removeCartRequest!!)
        }

    }

    var addWishListResponseLiveData = MutableLiveData<ResponseHandler<AddWishListResponse>>()

    fun addWishList(customerToken: String,productId: String) {
        viewModelScope.launch(coroutineContext) {
            addWishListResponseLiveData.value = ResponseHandler.Loading
            addWishListResponseLiveData.value = homeRepository?.addWishList(customerToken,productId)
        }

    }

    var removeWishListResponseLiveData = MutableLiveData<ResponseHandler<RemoveWishListResponse>>()

    fun removeWishList(customerToken: String,itemId:String) {
        viewModelScope.launch(coroutineContext) {
            removeWishListResponseLiveData.value = ResponseHandler.Loading
            removeWishListResponseLiveData.value = homeRepository?.removeWishList(customerToken,itemId)
        }

    }



}









/*
    private var homeResponce: MutableLiveData<BaseResponse<HomeResponse>> = MutableLiveData()
    val homeResponseLiveData: LiveData<BaseResponse<HomeResponse>>
        get() = homeResponce



    fun getHomeData(customerToken: String ) {
        viewModelScope.launch() {
            homeResponce.postValue(BaseResponse.Loading())
            try {
                val responce = homeRepository.getHomeData(customerToken)
                if (responce.isSuccessful) {
                    homeResponce.postValue(BaseResponse.Success(responce.body()))
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
