package com.example.alokozapshopapplication.ui.fragment.mywishlist.model.getwishlist


import com.fasterxml.jackson.annotation.JsonProperty

data class WishListResponse(
    @JsonProperty("eTag")
    var eTag: String? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("pageSize")
    var pageSize: String? = null,
    @JsonProperty("success")
    var success: Boolean? = null,
    @JsonProperty("totalCount")
    var totalCount: Int? = 0,
    @JsonProperty("wishList")
    var wishList: List<Wish> = listOf()
) {
    data class Wish(
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
        @JsonProperty("description")
        var description: Any? = null,
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
        @JsonProperty("id")
        var id: String? = null,
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
        @JsonProperty("options")
        var options: List<Any?>? = null,
        @JsonProperty("price")
        var price: Double? = null,
        @JsonProperty("productId")
        var productId: String? = null,
        @JsonProperty("qty")
        var qty: Int? = null,
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
}