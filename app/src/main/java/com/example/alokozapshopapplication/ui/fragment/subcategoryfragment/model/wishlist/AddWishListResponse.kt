package com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist


import com.fasterxml.jackson.annotation.JsonProperty

data class AddWishListResponse(
    @JsonProperty("itemId")
    var itemId: Int? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("success")
    var success: Boolean? = null
)