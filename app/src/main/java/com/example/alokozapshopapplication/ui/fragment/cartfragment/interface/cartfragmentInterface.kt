package com.example.alokozapshopapplication.ui.fragment.cartfragment.`interface`

import android.view.View
import com.example.alokozapshopapplication.ui.fragment.cartfragment.model.addtocartitem.AddToCartItemResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse

interface CartFragmentInterface {
    fun onAddWishList(position: Int, view: View, data: AddToCartItemResponse.Item)
    fun onDeleteWishList(position: Int, view: View, data: AddToCartItemResponse.Item)
    fun onWishListProductDetails(position: Int, view: View, data: AddToCartItemResponse.Item)
}