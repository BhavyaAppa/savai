package com.example.alokozapshopapplication.ui.fragment.loginfragment.loginmodelclass


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoginResponse(
    @JsonProperty("cartCount")
    var cartCount: Int? = null,
    @JsonProperty("customerEmail")
    var customerEmail: String? = null,
    @JsonProperty("customerId")
    var customerId: String? = null,
    @JsonProperty("customerName")
    var customerName: String? = null,
    @JsonProperty("customerToken")
    var customerToken: String? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("quoteId")
    var quoteId: Any? = null,
    @JsonProperty("success")
    var success: Boolean? = null
)

/*
@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginResponce(
    @JsonProperty("cartCount")
    var cartCount: Int? = null,
    @JsonProperty("customerEmail")
    var customerEmail: String? = null,
    @JsonProperty("customerId")
    var customerId: String? = null,
    @JsonProperty("customerName")
    var customerName: String? = null,
    @JsonProperty("customerToken")
    var customerToken: String? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("quoteId")
    var quoteId: Any? = null,
    @JsonProperty("success")
    var success: Boolean? = null
)*/
