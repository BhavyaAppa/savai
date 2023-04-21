package com.example.alokozapshopapplication.ui.fragment.countryfragment.model


import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)


data class CountryResponse(
    @JsonProperty("countryData")
    var countryData: List<CountryData>? = listOf(),
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("success")
    var success: Boolean? = null
) {


    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class CountryData(
        @JsonProperty("countryFlag")
        var countryFlag: String? = null,
        @JsonProperty("countryId")
        var countryId: String? = null,
        @JsonProperty("countryName")
        var countryName: String? = null,
        @JsonProperty("store")
        var store: String? = null,
        @JsonProperty("websiteId")
        var websiteId: String? = null,

        @JsonProperty("isSelected")
        var isSelected:Boolean=false


    )
}