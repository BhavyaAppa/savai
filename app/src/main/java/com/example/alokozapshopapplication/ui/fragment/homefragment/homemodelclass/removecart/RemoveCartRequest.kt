package com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.removecart

import com.fasterxml.jackson.annotation.JsonProperty

data class RemoveCartRequest(

    @JsonProperty("websiteId")
    var websiteId: String = "1",
    @JsonProperty("storeId")
    var storeId: String = "1",
    @JsonProperty("quoteId")
    var quoteId: String = "0",
    @JsonProperty("customerToken")
    var customerToken: String = "",
    @JsonProperty("currency")
    var currency: String = "AED",
    @JsonProperty("width")
    var width: String = "828.000000",
    @JsonProperty("itemId")
    var itemId: String = "",
)
