package com.example.alokozapshopapplication.ui.fragment.signupfragment.signupmodel

import com.fasterxml.jackson.annotation.JsonProperty

data class SignUpRequest(


    @JsonProperty("email")
    var email: String? = "",
    @JsonProperty("password")
    var password: String? = "",
    @JsonProperty("confirmPassword")
    var confirmPassword: String? = "",
    @JsonProperty("firstName")
    var firstName: String? = "",
    @JsonProperty("lastName")
    var lastName: String? = "",
    @JsonProperty("mobileNumberPrefix")
    var mobileNumberPrefix: String? = "1",
    @JsonProperty("isSocial")
    var isSocial: String? = "0",
    @JsonProperty("referralCode")
    var referralCode: String? = "",
    @JsonProperty("otp")
    var otp: String? = "",
    @JsonProperty("storeId")
    var storeId: String? = "1",
    @JsonProperty(" mobileNumber")
    var mobileNumber: String? = "",
    @JsonProperty("websiteId")
    var websiteId: String? = "1",
    @JsonProperty("mFactor")
    var mFactor: String? = "3.5",
    @JsonProperty("width")
    var width: String? = "1440",
    @JsonProperty("quoteId")
    var quoteId: String? = "0",
    @JsonProperty("token")
    var token: String? = "",
    @JsonProperty("os")
    var os: String? = "android"

)
