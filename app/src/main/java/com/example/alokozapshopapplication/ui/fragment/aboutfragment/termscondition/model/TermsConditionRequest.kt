package com.example.alokozapshopapplication.ui.fragment.aboutfragment.termscondition.model

import com.fasterxml.jackson.annotation.JsonProperty

data class TermsConditionRequest(

    @JsonProperty("id")
    var id: String? = "",

)
