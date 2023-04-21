package com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.`interface`

import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.timeslotmodel.TimeSlotResponse

interface TimeSlotInterface {

    fun isTimeSlotClick(position: Int,Data: TimeSlotResponse.Data)
    fun isTimeSlotTimeClick(position: Int, Data: TimeSlotResponse.Data.Slot, parentPos: Int)
}