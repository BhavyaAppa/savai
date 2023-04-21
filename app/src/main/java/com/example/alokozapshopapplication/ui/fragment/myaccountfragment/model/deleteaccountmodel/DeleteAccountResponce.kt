package com.example.alokozapshopapplication.ui.fragment.myaccountfragment.model.deleteaccountmodel


import com.fasterxml.jackson.annotation.JsonProperty

data class DeleteAccountResponce(
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("success")
    var success: Boolean? = false,
    @JsonProperty("otherError")
    var otherError: String? = null
)
