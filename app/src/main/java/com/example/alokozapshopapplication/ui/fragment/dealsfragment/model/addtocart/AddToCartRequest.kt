package com.example.alokozapshopapplication.ui.fragment.dealsfragment.model.addtocart

import com.fasterxml.jackson.annotation.JsonProperty

data class AddToCartRequest(


    @JsonProperty("websiteId")
    var websiteId: String? = "1",

    @JsonProperty(" customerToken")
    var customerToken: String? = "",

    @JsonProperty("currency")
    var currency: String? = "EUR",

    @JsonProperty("storeId")
    var storeId: String? = "3",

    @JsonProperty("productId")
    var productId: String? = "1034",

    @JsonProperty("quoteId")
    var quoteId: String? = "3",

    @JsonProperty("qty")
    var qty: String? = "1",

    @JsonProperty("width")
    var width: String? = "828.000000",

    )
