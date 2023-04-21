package com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.deliveryinfomodel

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty



@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class DeliveryInfoResponce(
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("status")
    var status: Int? = null
)