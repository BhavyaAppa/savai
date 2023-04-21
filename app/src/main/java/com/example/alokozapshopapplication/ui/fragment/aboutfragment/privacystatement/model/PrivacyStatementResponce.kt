package com.example.alokozapshopapplication.ui.fragment.aboutfragment.privacystatement.model


import com.fasterxml.jackson.annotation.JsonProperty

data class PrivacyStatementResponce(
    @JsonProperty("content")
    var content: String? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("success")
    var success: Boolean? = null,
    @JsonProperty("title")
    var title: String? = null
)