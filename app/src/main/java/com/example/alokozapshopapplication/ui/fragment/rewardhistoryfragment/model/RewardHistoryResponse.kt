package com.example.alokozapshopapplication.ui.fragment.rewardhistoryfragment.model


import com.fasterxml.jackson.annotation.JsonProperty

data class RewardHistoryResponse(
    @JsonProperty("customerPoints")
    var customerPoints: Int? = null,
    @JsonProperty("eTag")
    var eTag: String? = null,
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("success")
    var success: Boolean? = null,
    @JsonProperty("totalCount")
    var totalCount: String? = "0"
) {
    val total: String
        get() {
            return if (totalCount.isNullOrEmpty()) "0" else totalCount.orEmpty()
        }
}