package com.example.alokozapshopapplication.ui.fragment.dealsfragment.`interface`

import android.view.View
import com.example.alokozapshopapplication.ui.fragment.dealsfragment.model.deals.DealsResponse

interface DealsFragmentInterface {
    fun onProductDetail(position: Int, view: View, data: DealsResponse.Carousel.Product)
    fun onRemoveCart(position: Int, view: View, data: DealsResponse.Carousel.Product, count:Int=1)
    fun onAddCard(position: Int, view: View, data: DealsResponse.Carousel.Product, count:Int=1)
    fun onAddWishList(position: Int, view: View, data: DealsResponse.Carousel.Product)
    fun onRemoveWishList(position: Int, view: View, data: DealsResponse.Carousel.Product)

}

