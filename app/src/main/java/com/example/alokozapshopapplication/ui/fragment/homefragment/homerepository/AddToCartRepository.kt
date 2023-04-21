package com.example.alokozapshopapplication.ui.fragment.homefragment.homerepository

import com.example.alokozapshopapplication.ui.api.ApiRetrofitObject
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.removecart.RemoveCartResponse
import retrofit2.Response

class AddToCartRepository {
    suspend fun getAddToCart(customerToken:String,productId: String,quoteId: String): Response<AddToCartResponse> {
        return ApiRetrofitObject.api.getAddToCart( customerToken= customerToken, productId = productId,quoteId=quoteId )
    }

    suspend fun removeCart(customerToken:String,quoteId: String,itemId: String): Response<RemoveCartResponse> {
        return ApiRetrofitObject.api.removeCart(
            customerToken= customerToken,
            quoteId = quoteId,
            itemId = itemId )
    }


}