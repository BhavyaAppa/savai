package com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist

import com.fasterxml.jackson.annotation.JsonProperty

data class AddWishListRequest(
    @JsonProperty("storeId")
    var storeId: String = "1",
    @JsonProperty("websiteId")
    var websiteId: String = "1",
    @JsonProperty("customerToken")
    var customerToken: String = "",
    @JsonProperty("qty")
    var qty: String = "0",
    @JsonProperty("productId")
    var productId: String = "",

    )
