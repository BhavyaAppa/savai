package com.example.alokozapshopapplication.ui.fragment.cartfragment.model.emptycart


import com.fasterxml.jackson.annotation.JsonProperty

data class EmptyCartResponce(
    @JsonProperty("message")
    var message: String? = "",
    @JsonProperty("success")
    var success: Boolean? = false
)