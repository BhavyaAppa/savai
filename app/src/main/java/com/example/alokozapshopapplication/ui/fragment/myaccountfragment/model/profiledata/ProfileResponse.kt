package com.example.alokozapshopapplication.ui.fragment.myaccountfragment.model.profiledata


import com.fasterxml.jackson.annotation.JsonProperty

data class ProfileResponse(
    @JsonProperty("cartCount")
    var cartCount: Int? = null,
    @JsonProperty("dateFormat")
    var dateFormat: String? = null,
    @JsonProperty("eTag")
    var eTag: String? = null,
    @JsonProperty("email")
    var email: String? = null,
    @JsonProperty("firstName")
    var firstName: String? = null,
    @JsonProperty("isFaxVisible")
    var isFaxVisible: Boolean? = null,
    @JsonProperty("isTelephoneRequired")
    var isTelephoneRequired: Boolean? = null,
    @JsonProperty("isTelephoneVisible")
    var isTelephoneVisible: Boolean? = null,
    @JsonProperty("lastName")
    var lastName: String? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("mobileNumberPrefix")
    var mobileNumberPrefix: String? = null,
    @JsonProperty("mobilenumber")
    var mobilenumber: String? = null,
    @JsonProperty("orderTotal")
    var orderTotal: String? = null,
    @JsonProperty("prefixHasOptions")
    var prefixHasOptions: Boolean? = null,
    @JsonProperty("prefixOptions")
    var prefixOptions: List<String?>? = null,
    @JsonProperty("profileImage")
    var profileImage: String? = null,
    @JsonProperty("quoteId")
    var quoteId: String? = null,
    @JsonProperty("returnTotal")
    var returnTotal: String? = null,
    @JsonProperty("success")
    var success: Boolean? = null,
    @JsonProperty("suffixHasOptions")
    var suffixHasOptions: Boolean? = null,
    @JsonProperty("suffixOptions")
    var suffixOptions: List<String?>? = null,
    @JsonProperty("walletAmount")
    var walletAmount: String? = null
)