package com.example.alokozapshopapplication.ui.fragment.dealsfragment.model.deals

import com.fasterxml.jackson.annotation.JsonProperty

data class DealsRequest(

    @JsonProperty("eTag")
    var eTag: String = "",
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
    @JsonProperty("mFactor")
    var mFactor: String = "3.5",
    @JsonProperty("isFromUrl")
    var isFromUrl: String = "0",
    @JsonProperty("url")
    var url: String=""

)
