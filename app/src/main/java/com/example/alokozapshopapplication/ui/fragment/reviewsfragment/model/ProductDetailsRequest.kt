package com.example.alokozapshopapplication.ui.fragment.reviewsfragment.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductDetailsRequest(

    @JsonProperty("eTag")
    var eTag: String = "",
    @JsonProperty("websiteId")
    var websiteId: String = "1",
    @JsonProperty("storeId")
    var storeId: String = "1",
    @JsonProperty("quoteId")
    var quoteId: String ,
    @JsonProperty("customerToken")
    var customerToken: String ,
    @JsonProperty("currency")
    var currency: String = "AED",
    @JsonProperty("width")
    var width: String = "1440",
    @JsonProperty("productId")
    var productId: String

)
