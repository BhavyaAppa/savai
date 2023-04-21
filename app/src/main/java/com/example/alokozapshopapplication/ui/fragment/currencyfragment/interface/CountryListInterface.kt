package com.example.alokozapshopapplication.ui.fragment.currencyfragment.`interface`

import com.example.alokozapshopapplication.ui.fragment.currencyfragment.model.CurrencyResponse


interface CurrencyListInterface {

    fun isItemClick(position: Int,data: CurrencyResponse.AllowedCurrency)
}