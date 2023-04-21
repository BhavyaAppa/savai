package com.example.alokozapshopapplication.ui.fragment.productdetailsdata.repository

import com.example.alokozapshopapplication.ui.api.ApiRetrofitObject
import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.model.ProductDetailsResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.CategoryItemListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductDetailsRepository(val apiInterface: ApiInterface) : BaseRepository() {

/*    suspend fun productPageData(
        customerToken:String,
        quoteId: String,
        productId: String
    ) : Response<ProductDetailsResponse> {
        return ApiRetrofitObject.api.productPageData(
            customerToken=customerToken,
            quoteId = quoteId,
            productId = productId
        )
    }*/


    suspend fun productPageData(customerToken:String,
                                quoteId: String,
                                productId: String): ResponseHandler<ProductDetailsResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.productPageData(
                        customerToken=customerToken,
                        quoteId = quoteId,
                        productId = productId
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