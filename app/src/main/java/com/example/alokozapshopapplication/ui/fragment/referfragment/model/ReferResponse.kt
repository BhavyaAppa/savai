package com.example.alokozapshopapplication.ui.fragment.referfragment.model


import com.fasterxml.jackson.annotation.JsonProperty

data class ReferResponse(
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("referralCode")
    var referralCode: String? = null,
    @JsonProperty("success")
    var success: Boolean? = null,
    @JsonProperty("otherError")
    var otherError: Boolean? = null

)