package com.example.alokozapshopapplication.ui.fragment.cartfragment.repository

import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.cartfragment.model.addtocartitem.AddToCartItemResponse
import com.example.alokozapshopapplication.ui.fragment.cartfragment.model.emptycart.EmptyCartResponce
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddToCartDetailsRepository (val apiInterface: ApiInterface) : BaseRepository(){


    suspend fun getAddToCartDetails(customerToken: String,quoteId: String): ResponseHandler<AddToCartItemResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getAddToCartDetails(
                        customerToken=customerToken,
                        quoteId = quoteId

                     )
                })

        }
    }


    suspend fun getAddToCartEmpty(customerToken:String,quoteId: String): ResponseHandler<EmptyCartResponce> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getAddToCartEmpty(
                        customerToken=customerToken,
                        quoteId = quoteId

                    ) })
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



/*

    suspend fun getAddToCartDetails(customerToken:String,quoteId: String) : Response<AddToCartItemResponse> {
        return ApiRetrofitObject.api.getAddToCartDetails(customerToken=customerToken,quoteId = quoteId)
    }


    suspend fun getAddToCartEmpty(customerToken:String,quoteId: String) : Response<EmptyCartResponce> {
        return ApiRetrofitObject.api.getAddToCartEmpty(customerToken=customerToken,quoteId = quoteId)
    }
*/

}