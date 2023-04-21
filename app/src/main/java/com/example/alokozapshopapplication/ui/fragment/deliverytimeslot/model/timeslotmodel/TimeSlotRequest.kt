package com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.timeslotmodel

import com.fasterxml.jackson.annotation.JsonProperty

data class TimeSlotRequest(
    @JsonProperty("storeId")
    var storeId: String = "1",
)
