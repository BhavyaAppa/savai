package com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.deliveryinfomodel


import com.fasterxml.jackson.annotation.JsonProperty

data class DeliveryInfoRequest(
 @JsonProperty("quoteId")
  var quoteId: String? = null,
 @JsonProperty("status")
  var slotId: Int? = null

 )
