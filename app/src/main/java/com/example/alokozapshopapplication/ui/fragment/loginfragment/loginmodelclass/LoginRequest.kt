package com.example.alokozapshopapplication.ui.fragment.loginfragment.loginmodelclass

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class LoginRequest(

    @JsonProperty("username")
    var username: String? = "",
    @JsonProperty("password")
    var password: String? = "",
    @JsonProperty("storeId")
    var storeId: String? = "1",
    @JsonProperty("mobileNo")
    var mobileNo: String? = "",
    @JsonProperty("websiteId")
    var  websiteId: String? = "1",
    @JsonProperty("currency")
    var currency: String? = "AED",
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




/*@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginRequest(

    @JsonProperty("username")
    var username: String? = "mubarak.ansari@brainvire.com",
    @JsonProperty("password")
    var password: String? = "Brain@2022",
    @JsonProperty("storeId")
    var storeId: String? = "1",
    @JsonProperty("mobileNo")
    var mobileNo: String? = "",
    @JsonProperty("websiteId")
    var  websiteId: String? = "1",
    @JsonProperty("currency")
    var currency: String? = "AED",
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
)*/
