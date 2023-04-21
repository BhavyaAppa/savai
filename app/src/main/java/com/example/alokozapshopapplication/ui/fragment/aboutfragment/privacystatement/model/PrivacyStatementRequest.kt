package com.example.alokozapshopapplication.ui.fragment.aboutfragment.privacystatement.model

import com.fasterxml.jackson.annotation.JsonProperty

data class PrivacyStatementRequest(

    @JsonProperty("id")
    var id: String? = "",

)
