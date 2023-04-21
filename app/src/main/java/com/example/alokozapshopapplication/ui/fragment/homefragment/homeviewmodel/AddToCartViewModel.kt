package com.example.alokozapshopapplication.ui.fragment.homefragment.homeviewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartRequest
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.removecart.RemoveCartResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homerepository.AddToCartRepository
import com.example.alokozapshopapplication.ui.fragment.loginfragment.loginmodelclass.LoginRequest
import com.example.alokozapshopapplication.ui.utils.BaseResponse
import kotlinx.coroutines.launch

class AddToCartViewModel(private val addToCartRepository: AddToCartRepository) : ViewModel() {

    var addToCartRequest=AddToCartRequest()

    private var addToCartResponse: MutableLiveData<BaseResponse<AddToCartResponse>> = MutableLiveData()
    val addToCartResponseLiveData: LiveData<BaseResponse<AddToCartResponse>>
        get() = addToCartResponse


    fun getAddToCart(customerToken:String,productId: String,quoteId: String) {
        viewModelScope.launch() {
            addToCartResponse.postValue(BaseResponse.Loading())
            try {
                val responce = addToCartRepository.getAddToCart(customerToken,productId,quoteId)
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




    private var removeCartResponse: MutableLiveData<BaseResponse<RemoveCartResponse>> = MutableLiveData()
    val removeCartResponseLiveData: LiveData<BaseResponse<RemoveCartResponse>>
        get() = removeCartResponse


    fun removeCart(customerToken:String,quoteId: String,itemId: String) {
        viewModelScope.launch() {
            removeCartResponse.postValue(BaseResponse.Loading())
            try {
                val responce = addToCartRepository.removeCart(customerToken,quoteId,itemId)
                if (responce.isSuccessful) {
                    removeCartResponse.postValue(BaseResponse.Success(responce.body()))
                    Log.d("ResponseSucess", responce.body().toString())

                } else {
                    Log.d("ResponseError", responce.body().toString())
                }
            } catch (e: Exception) {
                Log.d("Exception", e.message.toString())
            }

        }

    }




}