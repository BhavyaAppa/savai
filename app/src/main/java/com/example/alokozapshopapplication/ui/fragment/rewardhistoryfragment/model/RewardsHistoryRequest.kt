package com.example.alokozapshopapplication.ui.fragment.rewardhistoryfragment.model

import com.fasterxml.jackson.annotation.JsonProperty

data class RewardsHistoryRequest(
    @JsonProperty("websiteId")
    var websiteId: String = "1",
    @JsonProperty("storeId")
    var storeId: String = "1",
    @JsonProperty("customerToken")
    var customerToken: String,
    @JsonProperty("pageNumber")
    var pageNumber: String = "1",
)
