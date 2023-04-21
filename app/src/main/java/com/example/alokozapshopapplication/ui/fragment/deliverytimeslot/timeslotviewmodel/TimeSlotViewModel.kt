/*package com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.timeslotviewmodel*/

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.timeslotmodel.TimeSlotResponse


import com.example.alokozapshopapplication.ui.utils.BaseResponse
import kotlinx.coroutines.launch

/*
class TimeSlotViewModel(private val timeSlotRepository: TimeSlotRepository) : ViewModel() {


    private var timeSlotResponse: MutableLiveData<BaseResponse<TimeSlotResponse>> = MutableLiveData()
    val timeSlotResponseLiveData: LiveData<BaseResponse<TimeSlotResponse>>
        get() = timeSlotResponse

    var selected:Boolean=false
    var fisrtSelected:Boolean=true


    fun getTimeSlot() {
        viewModelScope.launch() {
            timeSlotResponse.postValue(BaseResponse.Loading())
            try {
                val responce = timeSlotRepository.getTimeSlot()
                if (responce.isSuccessful) {
                    timeSlotResponse.postValue(BaseResponse.Success(responce.body()))
                    Log.d("ResponseSucess", responce.body().toString())

                } else {
                    Log.d("ResponseError", responce.body().toString())
                }
            } catch (e: Exception) {
                Log.d("Exception", e.message.toString())
            }

        }

    }

}*/
