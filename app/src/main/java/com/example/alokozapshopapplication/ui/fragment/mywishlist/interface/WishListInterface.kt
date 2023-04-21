package com.example.alokozapshopapplication.ui.fragment.mywishlist.`interface`

import android.view.View
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.getwishlist.WishListResponse

interface WishListInterface {
    fun onItemDelete(position: Int, view: View, data: WishListResponse.Wish)
    fun onAddCard(position: Int, view: View, data: WishListResponse.Wish, count:Int=1)
    fun onProductDetails(position: Int, view: View, data: WishListResponse.Wish)
}