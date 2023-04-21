package com.example.alokozapshopapplication.ui.fragment.countryfragment.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CountryRequest(

    @JsonProperty("platform")
    var platform: String? = null,
    @JsonProperty("version")
    var version: String? = null,

)
