package com.example.alokozapshopapplication.ui.fragment.mywishlist.model.getwishlist

import com.fasterxml.jackson.annotation.JsonProperty

data class WishListRequest(
    @JsonProperty("eTag")
    var eTag: String = "",
    @JsonProperty("storeId")
    var storeId: String = "1",
    @JsonProperty("quoteId")
    var quoteId: String ,
    @JsonProperty("customerToken")
    var customerToken: String ,
    @JsonProperty("currency")
    var currency: String = "AED"

)
