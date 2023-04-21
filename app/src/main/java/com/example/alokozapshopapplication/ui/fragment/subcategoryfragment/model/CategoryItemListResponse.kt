package com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

/*
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

data class CategoryItemListResponse(
    @JsonProperty("adultAge")
    var adultAge: Int? = null,
    @JsonProperty("cartCount")
    var cartCount: Int? = null,
    @JsonProperty("eTag")
    var eTag: String? = null,
    @JsonProperty("isAdult")
    var isAdult: Int? = null,
    @JsonProperty("isComingSoon")
    var isComingSoon: Int? = null,
    @JsonProperty("isHolyQuran")
    var isHolyQuran: Int? = null,
    @JsonProperty("layeredData")
    var layeredData: List<Any?>? = null,
    @JsonProperty("message")
    var message: String? = "",
    @JsonProperty("otherError")
    var otherError: String? = null,
    @JsonProperty("productList")
    var productList: ArrayList<Product> = arrayListOf(),
    @JsonProperty("sortingData")
    var sortingData: List<SortingData?>? = null,
    @JsonProperty("success")
    var success: Boolean? = null,
    @JsonProperty("totalCount")
    var totalCount: Int? = 0
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class Product(
        @JsonProperty("adultAge")
        var adultAge: String? = null,
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
        @JsonProperty("isAdult")
        var isAdult: Int? = null,
        @JsonProperty("isAvailable")
        var isAvailable: Boolean? = null,
        @JsonProperty("isComingSoon")
        var isComingSoon: Int? = null,
        @JsonProperty("isHolyQuran")
        var isHolyQuran: Int? = null,
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
        var minAddToCartQty: String? = null,
        @JsonProperty("name")
        var name: String? = null,
        @JsonProperty("price")
        var price: Double? = null,
        @JsonProperty("quoteItemQty")
        var quoteItemQty: Int? = null,
        @JsonProperty("rating")
        var rating: String? = null,
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
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class SortingData(
        @JsonProperty("code")
        var code: String? = null,
        @JsonProperty("label")
        var label: String? = null
    )
}
*/

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class CategoryItemListResponse(
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
    var productList: List<Product>? = listOf(),
    @JsonProperty("sortingData")
    var sortingData: List<SortingData?>? = null,
    @JsonProperty("success")
    var success: Boolean? = null,
    @JsonProperty("totalCount")
    var totalCount: Int? = null
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class ChildCategory(
        @JsonProperty("hasChildren")
        var hasChildren: Boolean = false,
        @JsonProperty("id")
        var id: String? = null,
        @JsonProperty("name")
        var name: String? = null
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class LayeredData(
        @JsonProperty("code")
        var code: String? = null,
        @JsonProperty("label")
        var label: String? = null,
        @JsonProperty("options")
        var options: List<Option?>? = null
    ) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        data class Option(
            @JsonProperty("count")
            var count: String? = null,
            @JsonProperty("id")
            var id: String? = null,
            @JsonProperty("label")
            var label: String? = null
        )
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
        var hasRequiredOptions: Boolean? = false,
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
        var minAddToCartQty: String? = null,
        @JsonProperty("name")
        var name: String? = null,
        @JsonProperty("price")
        var price: Double? = null,
        @JsonProperty("quoteItemQty")
        var quoteItemQty: Int? = null,
        @JsonProperty("rating")
        var rating: String? = null,
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
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class SortingData(
        @JsonProperty("code")
        var code: String? = null,
        @JsonProperty("label")
        var label: String? = null
    )
}


/*

data class CategoryItemListResponse(
    @JsonProperty("adultAge")
    var adultAge: Int? = null,
    @JsonProperty("cartCount")
    var cartCount: Int? = null,
    @JsonProperty("eTag")
    var eTag: String? = null,
    @JsonProperty("isAdult")
    var isAdult: Int? = null,
    @JsonProperty("isComingSoon")
    var isComingSoon: Int? = null,
    @JsonProperty("isHolyQuran")
    var isHolyQuran: Int? = null,
    @JsonProperty("layeredData")
    var layeredData: List<Any?>? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("otherError")
    var otherError: String? = null,
    @JsonProperty("productList")
    var productList: List<Product?>? = null,
    @JsonProperty("sortingData")
    var sortingData: List<SortingData?>? = null,
    @JsonProperty("success")
    var success: Boolean? = null,
    @JsonProperty("totalCount")
    var totalCount: Int? = null
) {
    data class Product(
        @JsonProperty("adultAge")
        var adultAge: String? = null,
        @JsonProperty("arTextureImages")
        var arTextureImages: List<Any?>? = null,
        @JsonProperty("arType")
        var arType: String? = null,
        @JsonProperty("arUrl")
        var arUrl: String? = null,
        @JsonProperty("availability")
        var availability: String? = null,
        @JsonProperty("categories")
        var categories: List<Category?>? = null,
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
        @JsonProperty("isAdult")
        var isAdult: Int? = null,
        @JsonProperty("isAvailable")
        var isAvailable: Boolean? = null,
        @JsonProperty("isComingSoon")
        var isComingSoon: Int? = null,
        @JsonProperty("isHolyQuran")
        var isHolyQuran: Int? = null,
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
        var rating: String? = null,
        @JsonProperty("reviewCount")
        var reviewCount: Int? = null,
        @JsonProperty("sellerTag")
        var sellerTag: String? = null,
        @JsonProperty("sku")
        var sku: String? = null,
        @JsonProperty("subCategories")
        var subCategories: List<SubCategory?>? = null,
        @JsonProperty("thumbNail")
        var thumbNail: String? = null,
        @JsonProperty("tierPrice")
        var tierPrice: String? = null,
        @JsonProperty("typeId")
        var typeId: String? = null,
        @JsonProperty("wishlistItemId")
        var wishlistItemId: Int? = null
    ) {
        data class Category(
            @JsonProperty("categoryId")
            var categoryId: String? = null,
            @JsonProperty("categoryName")
            var categoryName: String? = null
        )

        class ConfigurableData

        data class SubCategory(
            @JsonProperty("subCategoryId")
            var subCategoryId: String? = null,
            @JsonProperty("subCategoryName")
            var subCategoryName: String? = null
        )
    }

    data class SortingData(
        @JsonProperty("code")
        var code: String? = null,
        @JsonProperty("label")
        var label: String? = null
    )
}*/





