package com.example.alokozapshopapplication.ui.fragment.categoryfragment.model


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class CategoryItemResponse(
    @JsonProperty("categories")
    var categories: List<Category> = listOf(),
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("success")
    var success: Boolean? = null
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Category(
        @JsonProperty("adultAge")
        var adultAge: Int? = null,
        @JsonProperty("categorymobileImageName")
        var categorymobileImageName: String? = null,
        @JsonProperty("dominantColor")
        var dominantColor: String? = null,
        @JsonProperty("hasChildren")
        var hasChildren: Boolean? = null,
        @JsonProperty("id")
        var id: String? = null,
        @JsonProperty("isAdult")
        var isAdult: Int? = null,
        @JsonProperty("isComingSoon")
        var isComingSoon: Int? = null,
        @JsonProperty("isHolyQuran")
        var isHolyQuran: Int? = null,
        @JsonProperty("name")
        var name: String? = null,
        @JsonProperty("url")
        var url: String? = null
    )
}