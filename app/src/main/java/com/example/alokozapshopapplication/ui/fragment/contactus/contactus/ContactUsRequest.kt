package com.example.alokozapshopapplication.ui.fragment.contactus.contactus

import com.fasterxml.jackson.annotation.JsonProperty

data class ContactUsRequest(


    @JsonProperty("storeId")
    var storeId: String? = "1",
    @JsonProperty("name")
    var name: String? = "",
    @JsonProperty("email")
    var email: String? = "",
    @JsonProperty("telephone")
    var telephone: String? = "",
    @JsonProperty("comment")
    var comment: String? = "",


)
