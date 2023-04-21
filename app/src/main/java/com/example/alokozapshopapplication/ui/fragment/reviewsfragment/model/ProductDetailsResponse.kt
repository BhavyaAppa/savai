package com.example.alokozapshopapplication.ui.fragment.reviewsfragment.model


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

data class ProductDetailsResponse(
    @JsonProperty("additionalInformation")
    var additionalInformation: List<AdditionalInformation?>? = null,
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
    @JsonProperty("benefits")
    var benefits: String? = null,
    @JsonProperty("canGuestCheckoutDownloadable")
    var canGuestCheckoutDownloadable: Boolean? = null,
    @JsonProperty("cartCount")
    var cartCount: Int? = null,
    @JsonProperty("categories")
    var categories: List<Category?>? = null,
    @JsonProperty("description")
    var description: String? = null,
    @JsonProperty("dominantColor")
    var dominantColor: String? = null,
    @JsonProperty("eTag")
    var eTag: String? = null,
    @JsonProperty("finalPrice")
    var finalPrice: Int? = null,
    @JsonProperty("formattedFinalPrice")
    var formattedFinalPrice: String? = null,
    @JsonProperty("formattedMaxPrice")
    var formattedMaxPrice: String? = null,
    @JsonProperty("formattedMinPrice")
    var formattedMinPrice: String? = null,
    @JsonProperty("formattedMsrp")
    var formattedMsrp: String? = null,
    @JsonProperty("formattedPrice")
    var formattedPrice: String? = null,
    @JsonProperty("formattedSpecialPrice")
    var formattedSpecialPrice: String? = null,
    @JsonProperty("freeGiftMsg")
    var freeGiftMsg: String? = null,
    @JsonProperty("guestCanReview")
    var guestCanReview: Boolean? = null,
    @JsonProperty("id")
    var id: String? = null,
    @JsonProperty("imageGallery")
    var imageGallery: List<ImageGallery?>? = null,
    @JsonProperty("ingredients")
    var ingredients: String? = null,
    @JsonProperty("isAdult")
    var isAdult: Int? = null,
    @JsonProperty("isAllowedGuestCheckout")
    var isAllowedGuestCheckout: Boolean? = null,
    @JsonProperty("isAvailable")
    var isAvailable: Boolean? = null,
    @JsonProperty("isCheckoutAllowed")
    var isCheckoutAllowed: Boolean? = null,
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
    @JsonProperty("maxPrice")
    var maxPrice: Any? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("minAddToCartQty")
    var minAddToCartQty: Int? = null,
    @JsonProperty("minPrice")
    var minPrice: Any? = null,
    @JsonProperty("msrp")
    var msrp: Any? = null,
    @JsonProperty("msrpDisplayActualPriceType")
    var msrpDisplayActualPriceType: String? = null,
    @JsonProperty("msrpEnabled")
    var msrpEnabled: Any? = null,
    @JsonProperty("name")
    var name: String? = null,
    @JsonProperty("price")
    var price: Double? = null,
    @JsonProperty("priceFormat")
    var priceFormat: PriceFormat? = null,
    @JsonProperty("productUrl")
    var productUrl: String? = null,
    @JsonProperty("rating")
    var rating: String? = null,
    @JsonProperty("ratingArray")
    var ratingArray: RatingArray? = null,
    @JsonProperty("ratingData")
    var ratingData: List<RatingData?>? = null,
    @JsonProperty("ratingFormData")
    var ratingFormData: List<RatingFormData?>? = null,
    @JsonProperty("relatedProductList")
    var relatedProductList: List<RelatedProduct?>? = null,
    @JsonProperty("reviewCount")
    var reviewCount: Int? = null,
    @JsonProperty("reviewList")
    var reviewList: List<Review?>? = null,
    @JsonProperty("rewardMsgForProduct")
    var rewardMsgForProduct: String? = null,
    @JsonProperty("rewardMsgForReview")
    var rewardMsgForReview: String? = null,
    @JsonProperty("sellerTag")
    var sellerTag: String? = null,
    @JsonProperty("shortDescription")
    var shortDescription: String? = null,
    @JsonProperty("showBackInStockAlert")
    var showBackInStockAlert: Boolean? = null,
    @JsonProperty("showPriceDropAlert")
    var showPriceDropAlert: Boolean? = null,
    @JsonProperty("specialPrice")
    var specialPrice: Int? = null,
    @JsonProperty("subCategories")
    var subCategories: List<SubCategory?>? = null,
    @JsonProperty("success")
    var success: Boolean? = null,
    @JsonProperty("thumbNail")
    var thumbNail: String? = null,
    @JsonProperty("typeId")
    var typeId: String? = null,
    @JsonProperty("upsellProductList")
    var upsellProductList: List<UpsellProduct?>? = null
) {
    data class AdditionalInformation(
        @JsonProperty("label")
        var label: String? = null,
        @JsonProperty("value")
        var value: String? = null
    )

    data class Category(
        @JsonProperty("categoryId")
        var categoryId: String? = null,
        @JsonProperty("categoryName")
        var categoryName: String? = null
    )

    data class ImageGallery(
        @JsonProperty("dominantColor")
        var dominantColor: String? = null,
        @JsonProperty("isVideo")
        var isVideo: Boolean? = null,
        @JsonProperty("largeImage")
        var largeImage: String? = null,
        @JsonProperty("smallImage")
        var smallImage: String? = null,
        @JsonProperty("videoUrl")
        var videoUrl: String? = null
    )

    data class PriceFormat(
        @JsonProperty("decimalSymbol")
        var decimalSymbol: String? = null,
        @JsonProperty("groupLength")
        var groupLength: Int? = null,
        @JsonProperty("groupSymbol")
        var groupSymbol: String? = null,
        @JsonProperty("integerRequired")
        var integerRequired: Boolean? = null,
        @JsonProperty("pattern")
        var pattern: String? = null,
        @JsonProperty("precision")
        var precision: Int? = null,
        @JsonProperty("requiredPrecision")
        var requiredPrecision: Int? = null
    )

    data class RatingArray(
        @JsonProperty("1")
        var x1: Int? = null,
        @JsonProperty("2")
        var x2: Int? = null,
        @JsonProperty("3")
        var x3: Int? = null,
        @JsonProperty("4")
        var x4: Int? = null,
        @JsonProperty("5")
        var x5: Int? = null
    )

    data class RatingData(
        @JsonProperty("ratingCode")
        var ratingCode: String? = null,
        @JsonProperty("ratingValue")
        var ratingValue: String? = null
    )

    data class RatingFormData(
        @JsonProperty("id")
        var id: String? = null,
        @JsonProperty("name")
        var name: String? = null,
        @JsonProperty("values")
        var values: List<String?>? = null
    )

    data class RelatedProduct(
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

    data class Review(
        @JsonProperty("avgRatings")
        var avgRatings: String? = null,
        @JsonProperty("details")
        var details: String? = null,
        @JsonProperty("profileImage")
        var profileImage: String? = null,
        @JsonProperty("ratings")
        var ratings: List<Rating?>? = null,
        @JsonProperty("reviewBy")
        var reviewBy: String? = null,
        @JsonProperty("reviewOn")
        var reviewOn: String? = null,
        @JsonProperty("title")
        var title: String? = null
    ) {
        data class Rating(
            @JsonProperty("label")
            var label: String? = null,
            @JsonProperty("value")
            var value: String? = null
        )
    }

    data class SubCategory(
        @JsonProperty("subCategoryId")
        var subCategoryId: String? = null,
        @JsonProperty("subCategoryName")
        var subCategoryName: String? = null
    )

    data class UpsellProduct(
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
}