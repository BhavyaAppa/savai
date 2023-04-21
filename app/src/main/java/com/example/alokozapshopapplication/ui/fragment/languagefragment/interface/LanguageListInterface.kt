package com.example.alokozapshopapplication.ui.fragment.languagefragment.`interface`

import com.example.alokozapshopapplication.ui.fragment.countryfragment.model.CountryResponse
import com.example.alokozapshopapplication.ui.fragment.languagefragment.model.LanguageResponce


interface LanguageListInterface {

    fun isItemClick(position: Int,data:LanguageResponce.LanguageData)
}