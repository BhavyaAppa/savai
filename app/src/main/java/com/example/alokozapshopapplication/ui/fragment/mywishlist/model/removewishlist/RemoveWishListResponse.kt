package com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist


import com.fasterxml.jackson.annotation.JsonProperty

data class RemoveWishListResponse(
    @JsonProperty("alreadyDeleted")
    var alreadyDeleted: Boolean? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("success")
    var success: Boolean? = null
)