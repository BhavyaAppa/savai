package com.example.alokozapshopapplication.ui.fragment.searchfragment

import android.view.View
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.searchfragment.model.searchmodel.SearchDataRespoance

interface SearchListItemInterface {

    fun onProductClick(position: Int, view: View, data:SearchDataRespoance.Indice.Item)
    fun onProductItemClick(position: Int, view: View, data:SearchDataRespoance.Indice.Item)
}