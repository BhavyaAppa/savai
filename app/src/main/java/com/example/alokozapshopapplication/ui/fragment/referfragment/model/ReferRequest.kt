package com.example.alokozapshopapplication.ui.fragment.referfragment.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ReferRequest(
    @JsonProperty("websiteId")
    var websiteId: String = "1",
    @JsonProperty("storeId")
    var storeId: String = "1",
    @JsonProperty("quoteId")
    var quoteId: String,
    @JsonProperty("customerToken")
    var customerToken: String,
    @JsonProperty("currency")
    var currency: String = "AED",
    @JsonProperty("width")
    var width: String = "828.000000",
)
