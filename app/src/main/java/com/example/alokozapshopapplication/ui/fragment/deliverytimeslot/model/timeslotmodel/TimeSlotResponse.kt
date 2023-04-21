package com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.timeslotmodel


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class TimeSlotResponse(
    @JsonProperty("data")
    var data: List<Data> = listOf(),
    @JsonProperty("message")
    var message: String? = null,
    @JsonProperty("status")
    var status: Int? = null
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class Data(
        @JsonProperty("date")
        var date: String? = null,
        @JsonProperty("day")
        var day: String? = null,
        @JsonProperty("selected")
        var selectedDate: Boolean? = false,
        @JsonProperty("slots")
        var slots: List<Slot> = listOf()
    ) {

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        data class Slot(
            @JsonProperty("availability")
            var availability: Int? = null,
            @JsonProperty("enable")
            var enable: Boolean? = true,
            @JsonProperty("id")
            var id: String? = null,
            @JsonProperty("time")
            var time: String? = null,
            @JsonProperty("selected")
            var selectedTime: Boolean? = false
        )
    }
}