package com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.removecart


import com.fasterxml.jackson.annotation.JsonProperty

data class RemoveCartResponse(
    @JsonProperty("cartCount")
    var cartCount: Int? = null,
    @JsonProperty("freeGiftIds")
    var freeGiftIds: List<String?>? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("success")
    var success: Boolean? = null
)