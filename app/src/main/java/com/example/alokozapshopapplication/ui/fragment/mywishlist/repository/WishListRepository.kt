package com.example.alokozapshopapplication.ui.fragment.mywishlist.repository


import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.getwishlist.WishListResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class WishListRepository(val apiInterface: ApiInterface) : BaseRepository() {

    suspend fun wishList(customerToken:String,
                         pageNumber: Int): ResponseHandler<WishListResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.wishList(
                        customerToken=customerToken,
                        pageNumber = pageNumber
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

}