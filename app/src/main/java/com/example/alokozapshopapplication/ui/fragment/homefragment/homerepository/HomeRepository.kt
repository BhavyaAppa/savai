package com.example.alokozapshopapplication.ui.fragment.homefragment.homerepository


import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeRequest
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartRequest
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.removecart.RemoveCartRequest
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.removecart.RemoveCartResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse

import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class HomeRepository (val apiInterface: ApiInterface) : BaseRepository(){

/*    suspend fun getHomeData(customerToken: String): Response<HomeResponse> {
        return ApiRetrofitObject.api.getHomeData(customerToken=customerToken)
    }

    suspend fun getAddToCart(customerToken:String,productId: String,quoteId: String): Response<AddToCartResponse> {
        return ApiRetrofitObject.api.getAddToCart( customerToken= customerToken, productId = productId,quoteId=quoteId )
    }

    suspend fun removeCart(customerToken:String,quoteId: String,itemId: String): Response<RemoveCartResponse> {
        return ApiRetrofitObject.api.removeCart(
            customerToken= customerToken,
            quoteId = quoteId,
            itemId = itemId )
    }*/



    suspend fun getHomeData(customerToken: String ): ResponseHandler<HomeResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getHomeData(
                        customerToken = customerToken


                    )
                })
        }
    }

    suspend fun getAddToCart(customerToken: String,productId: String,quoteId: String ): ResponseHandler<AddToCartResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getAddToCart(
                        customerToken = customerToken.toString(),
                        productId = productId.toString(),
                        quoteId = quoteId.toString()
                    )
                })
        }
    }

    suspend fun removeCart(removeCartRequest: RemoveCartRequest): ResponseHandler<RemoveCartResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.removeCart(
                        customerToken = removeCartRequest.customerToken.toString(),
                        quoteId = removeCartRequest.quoteId.toString(),
                        itemId = removeCartRequest.itemId.toString()
                    )
                })
        }
    }

    suspend fun addWishList(customerToken: String,productId: String ): ResponseHandler<AddWishListResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.addWishList(
                        customerToken = customerToken,
                        productId = productId,

                        )
                })
        }
    }

    suspend fun removeWishList(customerToken:String,
                               itemId:String): ResponseHandler<RemoveWishListResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.removeWishList(
                        customerToken=customerToken,
                        itemId = itemId
                    )
                })
        }
    }

}