package com.example.alokozapshopapplication.ui.fragment.aboutfragment.newsmedia.model

import com.fasterxml.jackson.annotation.JsonProperty

data class NewsMediaRequest(

    @JsonProperty("id")
    var id: String? = "",

)
