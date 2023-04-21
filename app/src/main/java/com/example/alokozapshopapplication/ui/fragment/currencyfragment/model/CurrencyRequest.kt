package com.example.alokozapshopapplication.ui.fragment.currencyfragment.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CurrencyRequest(

    @JsonProperty("platform")
    var platform: String? = null,
    @JsonProperty("version")
    var version: String? = null,
    @JsonProperty("storeId")
    var storeId: String? = null,
)
