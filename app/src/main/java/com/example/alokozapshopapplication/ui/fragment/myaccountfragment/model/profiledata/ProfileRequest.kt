package com.example.alokozapshopapplication.ui.fragment.myaccountfragment.model.profiledata

import com.fasterxml.jackson.annotation.JsonProperty

data class ProfileRequest(

    @JsonProperty("eTag")
    var eTag: String? = "",

    @JsonProperty(" websiteId")
    var  websiteId: String? = "1",

    @JsonProperty(" storeId")
    var  storeId: String? = "1",

    @JsonProperty(" customerToken")
    var customerToken: String? = "1w1k3450sk7u2ljnhhq7stth9b2pgb8b"




)
