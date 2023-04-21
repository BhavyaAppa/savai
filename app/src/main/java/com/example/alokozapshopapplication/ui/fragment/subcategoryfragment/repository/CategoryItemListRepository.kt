package com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.repository

import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.CategoryItemListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CategoryItemListRepository (val apiInterface: ApiInterface) : BaseRepository() {

    suspend fun getCategoryItemList(customerToken: String,type:String ,id:String, pageNumber: Int): ResponseHandler<CategoryItemListResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getCategoryItemList(
                        customerToken=customerToken,
                        type=type,
                        id=id,
                        pageNumber = pageNumber
                    )
                })
        }
    }




    suspend fun getAddToCart(customerToken: String,productId: String,quoteId: String ): ResponseHandler<AddToCartResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getAddToCart(
                        customerToken = customerToken,
                        productId = productId,
                        quoteId = quoteId
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


    /*
    suspend fun getCategoryItemList(type:String ,id:String): Response<CategoryItemListResponse> {
        return ApiRetrofitObject.api.getCategoryItemList(type=type,id=id)
    }

    suspend fun getAddToCart(customerToken:String,productId: String,quoteId: String): Response<AddToCartResponse> {
        return ApiRetrofitObject.api.getAddToCart( customerToken= customerToken, productId = productId, quoteId=quoteId)
    }*/

}