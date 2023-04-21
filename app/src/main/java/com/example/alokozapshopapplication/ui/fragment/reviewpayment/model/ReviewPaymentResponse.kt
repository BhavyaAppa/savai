package com.example.alokozapshopapplication.ui.fragment.reviewpayment.model


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty



@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ReviewPaymentResponse(
    @JsonProperty("AndroidApplePay")
    var androidApplePay: String? = null,
    @JsonProperty("AndroidGooglePay")
    var androidGooglePay: String? = null,
    @JsonProperty("billingAddress")
    var billingAddress: String? = null,
    @JsonProperty("cartCount")
    var cartCount: Int? = null,
    @JsonProperty("cartTotal")
    var cartTotal: String? = null,
    @JsonProperty("couponCode")
    var couponCode: String? = null,
    @JsonProperty("creditCardData")
    var creditCardData: CreditCardData? = null,
    @JsonProperty("currencyCode")
    var currencyCode: String? = null,
    @JsonProperty("grandTotalAmount")
    var grandTotalAmount: String? = null,
    @JsonProperty("iOSApplePay")
    var iOSApplePay: String? = null,
    @JsonProperty("iOSGooglePay")
    var iOSGooglePay: String? = null,
    @JsonProperty("isRewardEnabled")
    var isRewardEnabled: Boolean? = null,
    @JsonProperty("isWalletEnabled")
    var isWalletEnabled: Boolean? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("orderReviewData")
    var orderReviewData: OrderReviewData? = null,
    @JsonProperty("paymentMethods")
    var paymentMethods: List<PaymentMethod?>? = null,
    @JsonProperty("shippingAddress")
    var shippingAddress: String? = null,
    @JsonProperty("success")
    var success: Boolean? = null,
    @JsonProperty("timeslotDetails")
    var timeslotDetails: TimeslotDetails? = null,
    @JsonProperty("totalDiscount")
    var totalDiscount: String? = null
) {
    data class CreditCardData(
        @JsonProperty("action")
        var action: String? = null,
        @JsonProperty("amount")
        var amount: Amount? = null
    ) {
        data class Amount(
            @JsonProperty("currencyCode")
            var currencyCode: String? = null,
            @JsonProperty("value")
            var value: Int? = null
        )
    }

    data class OrderReviewData(
        @JsonProperty("cartTotal")
        var cartTotal: Int? = null,
        @JsonProperty("items")
        var items: List<Item?>? = null,
        @JsonProperty("totals")
        var totals: List<Total?>? = null
    ) {
        data class Item(
            @JsonProperty("dominantColor")
            var dominantColor: String? = null,
            @JsonProperty("originalPrice")
            var originalPrice: String? = null,
            @JsonProperty("price")
            var price: String? = null,
            @JsonProperty("productName")
            var productName: String? = null,
            @JsonProperty("qty")
            var qty: Int? = null,
            @JsonProperty("subTotal")
            var subTotal: String? = null,
            @JsonProperty("thumbNail")
            var thumbNail: String? = null,
            @JsonProperty("unformattedOriginalPrice")
            var unformattedOriginalPrice: Double? = null,
            @JsonProperty("unformattedPrice")
            var unformattedPrice: Double? = null
        )

        data class Total(
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

    data class PaymentMethod(
        @JsonProperty("code")
        var code: String? = null,
        @JsonProperty("extraInformation")
        var extraInformation: String? = null,
        @JsonProperty("image")
        var image: String? = null,
        @JsonProperty("title")
        var title: String? = null
    )

    data class TimeslotDetails(
        @JsonProperty("orderDeliveryDate")
        var orderDeliveryDate: String? = null,
        @JsonProperty("orderDeliveryTime")
        var orderDeliveryTime: String? = null
    )
}