package com.example.alokozapshopapplication.ui.fragment.cartfragment.model.addtocartitem


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

data class AddToCartItemResponse(
    @JsonProperty("allowMultipleShipping")
    var allowMultipleShipping: Boolean? = null,
    @JsonProperty("amountToGetFreeGift")
    var amountToGetFreeGift: Int? = null,
    @JsonProperty("canGuestCheckoutDownloadable")
    var canGuestCheckoutDownloadable: Boolean? = null,
    @JsonProperty("cartCount")
    var cartCount: Int? = null,
    @JsonProperty("cartTotal")
    var cartTotal: String? = null,
    @JsonProperty("crossSellList")
    var crossSellList: List<Any?>? = null,
    @JsonProperty("freeGiftCheckoutText")
    var freeGiftCheckoutText: String? = null,
    @JsonProperty("freeGiftId")
    var freeGiftId: String? = null,
    @JsonProperty("freeGiftIds")
    var freeGiftIds: List<String?>? = null,
    @JsonProperty("freeGiftMsg")
    var freeGiftMsg: String? = null,
    @JsonProperty("freeGiftProductId")
    var freeGiftProductId: Int? = null,
    @JsonProperty("freeGiftProductId1")
    var freeGiftProductId1: Int? = null,
    @JsonProperty("freeShippingAmt")
    var freeShippingAmt: String? = null,
    @JsonProperty("freeShippingAmtUnformatted")
    var freeShippingAmtUnformatted: String? = null,
    @JsonProperty("freeShippingMsg")
    var freeShippingMsg: String? = null,
    @JsonProperty("freeShippingStartAmt")
    var freeShippingStartAmt: String? = null,
    @JsonProperty("isAllowedGuestCheckout")
    var isAllowedGuestCheckout: Boolean? = null,
    @JsonProperty("isCheckoutAllowed")
    var isCheckoutAllowed: Boolean? = null,
    @JsonProperty("isFreeGiftAllowed")
    var isFreeGiftAllowed: Boolean? = null,
    @JsonProperty("isfreeGiftAdded")
    var isfreeGiftAdded: Boolean? = null,
    @JsonProperty("items")
    var items: List<Item> = listOf(),
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("minimumAmount")
    var minimumAmount: Int? = null,
    @JsonProperty("minimumFormattedAmount")
    var minimumFormattedAmount: String? = null,
    @JsonProperty("quoteId")
    var quoteId: String? = null,
    @JsonProperty("showThreshold")
    var showThreshold: Boolean? = null,
    @JsonProperty("success")
    var success: Boolean? = null,
    @JsonProperty("timeslotDetails")
    var timeslotDetails: TimeslotDetails? = null,
    @JsonProperty("totalCount")
    var totalCount: Int? = null,
    @JsonProperty("totalDiscount")
    var totalDiscount: String? = null,
    @JsonProperty("totalsData")
    var totalsData: List<TotalsData?>? = null,
    @JsonProperty("unformattedCartTotal")
    var unformattedCartTotal: Double? = null,
    @JsonProperty("wishList")
    var wishList: List<Any?>? = null,
    @JsonProperty("wishListTotalCount")
    var wishListTotalCount: Int? = null
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class Item(
        @JsonProperty("canMoveToWishlist")
        var canMoveToWishlist: Boolean? = null,
        @JsonProperty("discountPrice")
        var discountPrice: String? = null,
        @JsonProperty("dominantColor")
        var dominantColor: String? = null,
        @JsonProperty("finalPrice")
        var finalPrice: String? = null,
        @JsonProperty("formattedFinalPrice")
        var formattedFinalPrice: String? = null,
        @JsonProperty("formattedPrice")
        var formattedPrice: String? = null,
        @JsonProperty("groupedProductId")
        var groupedProductId: Int? = null,
        @JsonProperty("id")
        var id: String? = null,
        @JsonProperty("image")
        var image: String? = null,
        @JsonProperty("isFreeProduct")
        var isFreeProduct: Boolean? = null,
        @JsonProperty("isInRange")
        var isInRange: Boolean? = null,
        @JsonProperty("messages")
        var messages: List<Message?>? = null,
        @JsonProperty("name")
        var name: String? = null,
        @JsonProperty("price")
        var price: Double? = null,
        @JsonProperty("productId")
        var productId: String? = null,
        @JsonProperty("qty")
        var qty: String? = null,
        @JsonProperty("remainingQty")
        var remainingQty: Int? = null,
        @JsonProperty("sku")
        var sku: String? = null,
        @JsonProperty("subTotal")
        var subTotal: String? = null,
        @JsonProperty("thresholdQty")
        var thresholdQty: String? = null,
        @JsonProperty("typeId")
        var typeId: String? = null
    ) {

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonInclude(JsonInclude.Include.NON_NULL)

        data class Message(
            @JsonProperty("text")
            var text: String? = null,
            @JsonProperty("type")
            var type: String? = null
        )
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class TimeslotDetails(
        @JsonProperty("deliverySlot")
        var deliverySlot: String? = null,
        @JsonProperty("orderDeliveryDate")
        var orderDeliveryDate: String? = null,
        @JsonProperty("orderDeliveryTime")
        var orderDeliveryTime: String? = null,
        @JsonProperty("showDeliverySlots")
        var showDeliverySlots: Boolean? = null
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    data class TotalsData(
        @JsonProperty("formattedValue")
        var formattedValue: String? = null,
        @JsonProperty("title")
        var title: String? = null,
        @JsonProperty("unformattedValue")
        var unformattedValue: Double? = null,
        @JsonProperty("value")
        var value: String? = null
    )
}