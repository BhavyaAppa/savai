package com.example.alokozapshopapplication.ui.fragment.languagefragment.model


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

data class LanguageResponce(
    @JsonProperty("languageData")
    var languageData: List<LanguageData>? = listOf(),
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("success")
    var success: Boolean? = null,
    @JsonProperty("timeStamp")
    var timeStamp: String? = null,
    @JsonProperty("websiteId")
    var websiteId: String? = null
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class LanguageData(
        @JsonProperty("code")
        var code: String? = null,
        @JsonProperty("downloadURL")
        var downloadURL: String? = null,
        @JsonProperty("isChange")
        var isChange: Int? = null,
        @JsonProperty("languageAlignment")
        var languageAlignment: String? = null,
        @JsonProperty("languageId")
        var languageId: String? = null,
        @JsonProperty("languageName")
        var languageName: String? = null,
        @JsonProperty("languageSortCode")
        var languageSortCode: String? = null,
        @JsonProperty("storeId")
        var storeId: String? = null,
        @JsonProperty("timeStamp")
        var timeStamp: String? = null,
        @JsonProperty("url")
        var url: String? = null,

        @JsonProperty("isSelected")
        var isSelected: Boolean = false
    )
}