package com.example.alokozapshopapplication.ui.fragment.countryfragment.`interface`

import com.example.alokozapshopapplication.ui.fragment.countryfragment.model.CountryResponse


interface CountryListInterface {

    fun isItemClick(position: Int,data:CountryResponse.CountryData)
}