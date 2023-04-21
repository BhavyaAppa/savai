package com.example.alokozapshopapplication.ui.fragment.categoryfragment.`interface`

import android.view.View
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.model.CategoryItemResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.CategoryItemListResponse

interface CategoryItemInterFace {

    fun onClick(position: Int, view: View, data: CategoryItemResponse.Category)
    fun onClickOnItem(position: Int, view: View)

    fun onAddToCartClick(position: Int, view: View, data: CategoryItemListResponse.Product)

    fun onProductDetailClick(position: Int, view: View, data: CategoryItemListResponse.Product)
    fun onRemoveCard(position: Int, view: View, data: CategoryItemListResponse.Product, count:Int=1)
    fun onAddCard(position: Int, view: View, data: CategoryItemListResponse.Product, count:Int=1)

    fun wishList(position: Int, view: View,data: CategoryItemListResponse.Product)

}