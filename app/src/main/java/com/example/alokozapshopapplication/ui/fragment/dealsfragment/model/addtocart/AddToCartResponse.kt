package com.example.alokozapshopapplication.ui.fragment.dealsfragment.model.addtocart


import com.fasterxml.jackson.annotation.JsonProperty

data class AddToCartResponse(
    @JsonProperty("cartCount")
    var cartCount: Int? = null,
    @JsonProperty("isVirtual")
    var isVirtual: Boolean? = null,
    @JsonProperty("itemId")
    var itemId: String? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("quoteId")
    var quoteId: String? = null,
    @JsonProperty("success")
    var success: Boolean? = null
)