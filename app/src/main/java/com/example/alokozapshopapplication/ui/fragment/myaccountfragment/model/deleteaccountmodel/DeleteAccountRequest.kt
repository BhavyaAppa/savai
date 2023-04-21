package com.example.alokozapshopapplication.ui.fragment.myaccountfragment.model.deleteaccountmodel

import com.fasterxml.jackson.annotation.JsonProperty

data class DeleteAccountRequest(

    @JsonProperty("storeId")
    var storeId: String? = "3",

    @JsonProperty("currency")
    var currency: String? = "EUR",

    @JsonProperty("quoteId")
    var quoteId: String? = "3",

    @JsonProperty("width")
    var width: String? = "828.000000",

    @JsonProperty(" customerToken")
    var customerToken: String? = "",

    @JsonProperty("websiteId")
    var websiteId: String? = "2"


)