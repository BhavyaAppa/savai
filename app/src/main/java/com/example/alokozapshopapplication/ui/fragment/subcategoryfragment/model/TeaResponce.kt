package com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model


import com.fasterxml.jackson.annotation.JsonProperty

data class TeaResponce(
    @JsonProperty("cartCount")
    var cartCount: Int? = null,
    @JsonProperty("childCategories")
    var childCategories: List<ChildCategory?>? = null,
    @JsonProperty("eTag")
    var eTag: String? = null,
    @JsonProperty("layeredData")
    var layeredData: List<LayeredData?>? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("productList")
    var productList: List<Product?>? = null,
    @JsonProperty("sortingData")
    var sortingData: List<SortingData?>? = null,
    @JsonProperty("success")
    var success: Boolean? = null,
    @JsonProperty("totalCount")
    var totalCount: Int? = null
) {
    data class ChildCategory(
        @JsonProperty("hasChildren")
        var hasChildren: Boolean? = null,
        @JsonProperty("id")
        var id: String? = null,
        @JsonProperty("name")
        var name: String? = null
    )

    data class LayeredData(
        @JsonProperty("code")
        var code: String? = null,
        @JsonProperty("label")
        var label: String? = null,
        @JsonProperty("options")
        var options: List<Option?>? = null
    ) {
        data class Option(
            @JsonProperty("count")
            var count: String? = null,
            @JsonProperty("id")
            var id: String? = null,
            @JsonProperty("label")
            var label: String? = null
        )
    }

    data class Product(
        @JsonProperty("arTextureImages")
        var arTextureImages: List<Any?>? = null,
        @JsonProperty("arType")
        var arType: String? = null,
        @JsonProperty("arUrl")
        var arUrl: String? = null,
        @JsonProperty("availability")
        var availability: String? = null,
        @JsonProperty("configurableData")
        var configurableData: ConfigurableData? = null,
        @JsonProperty("dominantColor")
        var dominantColor: String? = null,
        @JsonProperty("entityId")
        var entityId: String? = null,
        @JsonProperty("finalPrice")
        var finalPrice: Double? = null,
        @JsonProperty("formattedFinalPrice")
        var formattedFinalPrice: String? = null,
        @JsonProperty("formattedPrice")
        var formattedPrice: String? = null,
        @JsonProperty("formattedTierPrice")
        var formattedTierPrice: String? = null,
        @JsonProperty("hasRequiredOptions")
        var hasRequiredOptions: Boolean? = null,
        @JsonProperty("isAvailable")
        var isAvailable: Boolean? = null,
        @JsonProperty("isComingSoon")
        var isComingSoon: Int? = null,
        @JsonProperty("isInRange")
        var isInRange: Boolean? = null,
        @JsonProperty("isInWishlist")
        var isInWishlist: Boolean? = null,
        @JsonProperty("isNew")
        var isNew: Boolean? = null,
        @JsonProperty("isQuote")
        var isQuote: Int? = null,
        @JsonProperty("itemId")
        var itemId: Int? = null,
        @JsonProperty("minAddToCartQty")
        var minAddToCartQty: Int? = null,
        @JsonProperty("name")
        var name: String? = null,
        @JsonProperty("price")
        var price: Double? = null,
        @JsonProperty("quoteItemQty")
        var quoteItemQty: Int? = null,
        @JsonProperty("rating")
        var rating: Int? = null,
        @JsonProperty("reviewCount")
        var reviewCount: Int? = null,
        @JsonProperty("sellerTag")
        var sellerTag: String? = null,
        @JsonProperty("thumbNail")
        var thumbNail: String? = null,
        @JsonProperty("tierPrice")
        var tierPrice: String? = null,
        @JsonProperty("typeId")
        var typeId: String? = null,
        @JsonProperty("wishlistItemId")
        var wishlistItemId: Int? = null
    ) {
        class ConfigurableData
    }

    data class SortingData(
        @JsonProperty("code")
        var code: String? = null,
        @JsonProperty("label")
        var label: String? = null
    )
}