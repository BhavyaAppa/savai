package com.example.alokozapshopapplication.ui.fragment.reviewpayment.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ReviewPaymentRequest(


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
    var width: String = "1440",
    @JsonProperty("method")
    var method: String = "customer",
    @JsonProperty("shippingMethod")
    var shippingMethod: String = "",


    )
