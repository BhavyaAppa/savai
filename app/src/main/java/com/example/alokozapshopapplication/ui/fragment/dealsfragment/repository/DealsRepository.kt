package com.example.alokozapshopapplication.ui.fragment.dealsfragment.repository

import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.dealsfragment.model.deals.DealsResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.removecart.RemoveCartRequest
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.removecart.RemoveCartResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DealsRepository (val apiInterface: ApiInterface) : BaseRepository() {

    suspend fun getDealsData(customerToken: String ): ResponseHandler<DealsResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getDealsData(
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

    suspend fun removeWishList(customerToken:String,itemId:String): ResponseHandler<RemoveWishListResponse> {
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