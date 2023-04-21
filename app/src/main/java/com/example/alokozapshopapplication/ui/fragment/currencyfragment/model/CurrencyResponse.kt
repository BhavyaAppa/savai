package com.example.alokozapshopapplication.ui.fragment.currencyfragment.model


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class CurrencyResponse(
    @JsonProperty("allowedCurrencies")
    var allowedCurrencies: ArrayList<AllowedCurrency>? = arrayListOf(),
    @JsonProperty("defaultCurrency")
    var defaultCurrency: String="",
    @JsonProperty("message")
    var message: String? = "",
    @JsonProperty("success")
    var success: Boolean? = false
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class AllowedCurrency(
        @JsonProperty("code")
        var code: String? = "",
        @JsonProperty("label")
        var label: String? = "",
        @JsonProperty("isSelected")
    var isSelected:Boolean=false
    )
}