package com.example.alokozapshopapplication.ui.fragment.searchfragment.model.searchmodel


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchDataRespoance(
    @JsonProperty("indices")
    var indices: List<Indice>? = listOf(),
    @JsonProperty("noResults")
    var noResults: Boolean? = null,
    @JsonProperty("optimize")
    var optimize: Boolean? = null,
    @JsonProperty("query")
    var query: String? = null,
    @JsonProperty("textAll")
    var textAll: String? = null,
    @JsonProperty("textEmpty")
    var textEmpty: String? = null,
    @JsonProperty("totalItems")
    var totalItems: Int? = null,
    @JsonProperty("urlAll")
    var urlAll: String? = null
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class Indice(
        @JsonProperty("identifier")
        var identifier: String? = null,
        @JsonProperty("isShowTotals")
        var isShowTotals: Boolean? = null,
        @JsonProperty("items")
        var items: List<Item>? = listOf(),
        @JsonProperty("order")
        var order: Int? = null,
        @JsonProperty("title")
        var title: String? = null,
        @JsonProperty("totalItems")
        var totalItems: Int? = null
    ) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        data class Item(
            @JsonProperty("cart")
            var cart: Cart? = null,
            @JsonProperty("description")
            var description: String? = null,
            @JsonProperty("image")
            var image: String? = null,
            @JsonProperty("name")
            var name: String? = null,
            @JsonProperty("num_results")
            var numResults: String? = null,
            @JsonProperty("optimize")
            var optimize: Boolean? = null,
            @JsonProperty("popularity")
            var popularity: String? = null,
            @JsonProperty("price")
            var price: String? = null,
            @JsonProperty("query_text")
            var queryText: String? = null,
            @JsonProperty("rating")
            var rating: String? = null,
            @JsonProperty("sku")
            var sku: String? = null,
            @JsonProperty("url")
            var url: String? = null
        ) {
            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonInclude(JsonInclude.Include.NON_NULL)
            data class Cart(
                @JsonProperty("label")
                var label: String? = null,
                @JsonProperty("params")
                var params: Params? = null,
                @JsonProperty("visible")
                var visible: Boolean? = null
            ) {
                @JsonIgnoreProperties(ignoreUnknown = true)
                @JsonInclude(JsonInclude.Include.NON_NULL)
                data class Params(
                    @JsonProperty("action")
                    var action: String? = null,
                    @JsonProperty("data")
                    var `data`: Data? = null
                ) {
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    @JsonInclude(JsonInclude.Include.NON_NULL)
                    data class Data(
                        @JsonProperty("product")
                        var product: String? = null,
                        @JsonProperty("uenc")
                        var uenc: String? = null
                    )
                }
            }
        }
    }
}