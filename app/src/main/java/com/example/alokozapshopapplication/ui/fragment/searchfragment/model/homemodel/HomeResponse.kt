package com.example.alokozapshopapplication.ui.fragment.searchfragment.model.homemodel


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

data class HomeResponse(
    @JsonProperty("allowAndroidDownload")
    var allowAndroidDownload: Boolean? = null,
    @JsonProperty("allowIosDownload")
    var allowIosDownload: Boolean? = null,
    @JsonProperty("allowedCurrencies")
    var allowedCurrencies: List<AllowedCurrency?>? = null,
    @JsonProperty("androidDownloadLink")
    var androidDownloadLink: Any? = null,
    @JsonProperty("appButtonColor")
    var appButtonColor: String? = null,
    @JsonProperty("appLogo")
    var appLogo: String? = null,
    @JsonProperty("appLogoDominantColor")
    var appLogoDominantColor: String? = null,
    @JsonProperty("appThemeColor")
    var appThemeColor: String? = null,
    @JsonProperty("appThemeTextColor")
    var appThemeTextColor: String? = null,
    @JsonProperty("bannerImages")
    var bannerImages: List<BannerImage?>? = null,
    @JsonProperty("buttonTextColor")
    var buttonTextColor: String? = null,
    @JsonProperty("carousel")
    var carousel: List<Carousel?>? = null,
    @JsonProperty("categories")
    var categories: List<Category?>? = null,
    @JsonProperty("cmsData")
    var cmsData: List<CmsData?>? = null,
    @JsonProperty("code")
    var code: String? = null,
    @JsonProperty("custom")
    var custom: Custom? = null,
    @JsonProperty("darkAppButtonColor")
    var darkAppButtonColor: String? = null,
    @JsonProperty("darkAppLogo")
    var darkAppLogo: String? = null,
    @JsonProperty("darkAppLogoDominantColor")
    var darkAppLogoDominantColor: String? = null,
    @JsonProperty("darkAppThemeColor")
    var darkAppThemeColor: String? = null,
    @JsonProperty("darkAppThemeTextColor")
    var darkAppThemeTextColor: String? = null,
    @JsonProperty("darkButtonTextColor")
    var darkButtonTextColor: String? = null,
    @JsonProperty("defaultCurrency")
    var defaultCurrency: String? = null,
    @JsonProperty("eTag")
    var eTag: String? = null,
    @JsonProperty("featuredCategories")
    var featuredCategories: List<FeaturedCategory?>? = null,
    @JsonProperty("iosDownloadLink")
    var iosDownloadLink: Any? = null,
    @JsonProperty("isMarquee")
    var isMarquee: String? = null,
    @JsonProperty("isOffer")
    var isOffer: String? = null,
    @JsonProperty("marquee")
    var marquee: Marquee? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("offer")
    var offer: Offer? = null,
    @JsonProperty("priceFormat")
    var priceFormat: PriceFormat? = null,
    @JsonProperty("quoteId")
    var quoteId: String? = null,
    @JsonProperty("showSwatchOnCollection")
    var showSwatchOnCollection: Boolean? = null,
    @JsonProperty("sort_order")
    var sortOrder: List<SortOrder?>? = null,
    @JsonProperty("storeData")
    var storeData: List<StoreData?>? = null,
    @JsonProperty("success")
    var success: Boolean? = null,
    @JsonProperty("tabCategoryView")
    var tabCategoryView: String? = null,
    @JsonProperty("themeCode")
    var themeCode: Any? = null,
    @JsonProperty("themeType")
    var themeType: Int? = null,
    @JsonProperty("url")
    var url: String? = null,
    @JsonProperty("websiteData")
    var websiteData: List<WebsiteData?>? = null,
    @JsonProperty("wishlistEnable")
    var wishlistEnable: Boolean? = null
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class AllowedCurrency(
        @JsonProperty("code")
        var code: String? = null,
        @JsonProperty("label")
        var label: String? = null
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class BannerImage(
        @JsonProperty("bannerType")
        var bannerType: String? = null,
        @JsonProperty("dominantColor")
        var dominantColor: String? = null,
        @JsonProperty("id")
        var id: String? = null,
        @JsonProperty("name")
        var name: String? = null,
        @JsonProperty("url")
        var url: String? = null
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class Carousel(
        @JsonProperty("banners")
        var banners: List<Banner?>? = null,
        @JsonProperty("id")
        var id: String? = null,
        @JsonProperty("label")
        var label: String? = null,
        @JsonProperty("productList")
        var productList: List<Product?>? = null,
        @JsonProperty("type")
        var type: String? = null
    ) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonInclude(JsonInclude.Include.NON_NULL)

        data class Banner(
            @JsonProperty("bannerType")
            var bannerType: String? = null,
            @JsonProperty("dominantColor")
            var dominantColor: String? = null,
            @JsonProperty("id")
            var id: String? = null,
            @JsonProperty("name")
            var name: String? = null,
            @JsonProperty("title")
            var title: String? = null,
            @JsonProperty("url")
            var url: String? = null
        )

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
            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonInclude(JsonInclude.Include.NON_NULL)
            class ConfigurableData
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class Category(
        @JsonProperty("hasChildren")
        var hasChildren: Boolean? = null,
        @JsonProperty("id")
        var id: String? = null,
        @JsonProperty("name")
        var name: String? = null
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class CmsData(
        @JsonProperty("id")
        var id: String? = null,
        @JsonProperty("title")
        var title: String? = null
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class Custom(
        @JsonProperty("bannerWidth")
        var bannerWidth: Int? = null,
        @JsonProperty("current")
        var current: Int? = null,
        @JsonProperty("width")
        var width: String? = null
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class FeaturedCategory(
        @JsonProperty("adultAge")
        var adultAge: Int? = null,
        @JsonProperty("categoryId")
        var categoryId: String? = null,
        @JsonProperty("categoryName")
        var categoryName: String? = null,
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

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class Marquee(
        @JsonProperty("bgcolor")
        var bgcolor: String? = null,
        @JsonProperty("color")
        var color: String? = null,
        @JsonProperty("id")
        var id: String? = null,
        @JsonProperty("label")
        var label: String? = null,
        @JsonProperty("type")
        var type: String? = null
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class Offer(
        @JsonProperty("id")
        var id: String? = null,
        @JsonProperty("label")
        var label: String? = null
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

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

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class SortOrder(
        @JsonProperty("id")
        var id: String? = null,
        @JsonProperty("label")
        var label: String? = null,
        @JsonProperty("layout_id")
        var layoutId: String? = null,
        @JsonProperty("position")
        var position: String? = null,
        @JsonProperty("type")
        var type: String? = null
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class StoreData(
        @JsonProperty("id")
        var id: String? = null,
        @JsonProperty("name")
        var name: String? = null,
        @JsonProperty("stores")
        var stores: List<Store?>? = null
    ) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonInclude(JsonInclude.Include.NON_NULL)

        data class Store(
            @JsonProperty("code")
            var code: String? = null,
            @JsonProperty("id")
            var id: String? = null,
            @JsonProperty("name")
            var name: String? = null
        )
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class WebsiteData(
        @JsonProperty("id")
        var id: String? = null,
        @JsonProperty("name")
        var name: String? = null
    )
}