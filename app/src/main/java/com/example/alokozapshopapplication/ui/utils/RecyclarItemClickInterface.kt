package com.example.alokozapshopapplication.ui.utils

import android.view.View
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.model.CategoryItemResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse

interface RecyclarItemClickInterface {

    fun onClick(position: Int, view: View, data: CategoryItemResponse.Category)

    fun onClickOnItem(position: Int, view: View)

    fun onClickHomeCategory(position: Int, view: View, data: HomeResponse.FeaturedCategory)




}