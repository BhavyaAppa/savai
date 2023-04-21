package com.example.alokozapshopapplication.ui.fragment.contactus.contactus


import com.fasterxml.jackson.annotation.JsonProperty

data class ContactUsResponse(
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("success")
    var success: Boolean? = null
)