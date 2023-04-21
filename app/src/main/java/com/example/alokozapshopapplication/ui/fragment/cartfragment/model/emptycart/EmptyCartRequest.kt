package com.example.alokozapshopapplication.ui.fragment.cartfragment.model.emptycart

import com.fasterxml.jackson.annotation.JsonProperty


data class EmptyCartRequest(
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
)
