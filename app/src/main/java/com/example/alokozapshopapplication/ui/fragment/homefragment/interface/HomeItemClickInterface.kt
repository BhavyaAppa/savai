package com.example.alokozapshopapplication.ui.fragment.homefragment.`interface`

import android.view.View
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse

interface HomeFragmentItemClickInterface {
    fun onItemClick(position: Int, view: View, data: HomeResponse.Carousel)
    fun onSubItemClick(position: Int, view: View, data: HomeResponse.Carousel.Product)
    fun onAddToCartClick(position: Int, view: View, data: HomeResponse.Carousel.Product)
    fun onClickFeaturedCategory(position: Int, view: View, data: HomeResponse.FeaturedCategory)


    fun onRemoveCard(position: Int, view: View, data: HomeResponse.Carousel.Product, count:Int=1)
    fun onAddCard(position: Int, view: View, data: HomeResponse.Carousel.Product, count:Int=1)

    fun onAddWishList(position: Int, view: View, data: HomeResponse.Carousel.Product)
    fun onRemoveWishList(position: Int, view: View, data: HomeResponse.Carousel.Product)
}