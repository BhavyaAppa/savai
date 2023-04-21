package com.example.alokozapshopapplication.ui.fragment.searchfragment.`interface`

import android.view.View
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse

interface SearchCategoryInterface {

    fun onClickCategory(position: Int, view: View, data: HomeResponse.FeaturedCategory)

}