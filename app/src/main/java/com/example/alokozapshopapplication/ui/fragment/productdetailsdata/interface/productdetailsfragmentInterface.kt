package com.example.alokozapshopapplication.ui.fragment.productdetailsdata.`interface`

import android.view.View
import com.example.alokozapshopapplication.ui.fragment.cartfragment.model.addtocartitem.AddToCartItemResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.model.ProductDetailsResponse

interface ProductDetailsFragmentInterface {
    fun onAddWishList(position: Int, view: View, data: AddToCartItemResponse.Item)
    fun onDeleteWishList(position: Int, view: View, data: AddToCartItemResponse.Item)
    fun onProductRecent(position: Int, view: View, data: ProductDetailsResponse.RelatedProduct)
    fun onProductUpSell(position: Int, view: View, data: ProductDetailsResponse.UpsellProduct)
}