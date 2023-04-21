package com.example.alokozapshopapplication.ui.fragment.languagefragment.model

import com.fasterxml.jackson.annotation.JsonProperty

data class LanguageRequest(


    @JsonProperty("platform")
    var platform: String? = null,
    @JsonProperty("version")
    var version: String? = null,
    @JsonProperty("countryId")
    var countryId: String? = "1",
    @JsonProperty("websiteId")
    var websiteId: String? = "1",
    @JsonProperty("timeStamp")
    var timeStamp: String? = "",
)
