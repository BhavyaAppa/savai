package com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentTimeSlotBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.`interface`.TimeSlotInterface
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.adapter.TimeSlotAdapter
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.adapter.TimeSlotTimeAdapter
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.deliveryinfomodel.DeliveryInfoResponce
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.timeslotmodel.TimeSlotResponse
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.timeslotviewmodel.DeliveryInfoViewModel

import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey

class TimeSlotFragment : FragmentBase<DeliveryInfoViewModel, FragmentTimeSlotBinding>(){

    private lateinit var deliveryInfoViewModel: DeliveryInfoViewModel
    private lateinit var timeSlotAdapter: TimeSlotAdapter
    private lateinit var timeSlotTimeAdapter: TimeSlotTimeAdapter

    private var dateList = ArrayList<TimeSlotResponse.Data>()
    private var timeList = ArrayList<TimeSlotResponse.Data.Slot>()

    private var sloteId: String? = null

    val customerToken = MyPreference.getValueString(PrefKey.ACCESS_TOKEN, "")
    val quoteId = MyPreference.getValueString(PrefKey.QUOTE_ID, "")




    private fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run {
            navigate(direction)
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_time_slot

    override fun setupToolbar() {
        (activity as MainActivity).setToolbarTitle(
            imageCenter = false,
            imageArrowSearch = true,
            imageArrow = false,
            imageSearch = false,
            true


        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deliveryInfoViewModel.getTimeSlot()
        deliveryInfoViewModel.selected = true
        deliveryInfoViewModel.fisrtSelected = false
        setLiveDataObservers()
    }

    override fun initializeScreenVariables() {


        getDataBinding().lifecycleOwner=viewLifecycleOwner
        getDataBinding().timeSlotViewModel=deliveryInfoViewModel



        getDataBinding().btnProceedBuy.setOnClickListener {
            if (sloteId == null){
                Toast.makeText(requireContext(), "Please select a slot", Toast.LENGTH_SHORT).show()
            }else {
                sloteId?.let { it1 ->
                    deliveryInfoViewModel.getTimeDateSlot(
                        quoteId = quoteId.toString(),
                        slotId = it1
                    )
                }
            }
        }



    }

    override fun getViewModel(): DeliveryInfoViewModel {
        deliveryInfoViewModel = ViewModelProvider(this)[DeliveryInfoViewModel::class.java]
        return deliveryInfoViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapterDay() {
        timeSlotAdapter = TimeSlotAdapter(requireContext(), object : TimeSlotInterface {
            override fun isTimeSlotClick(position: Int, Data: TimeSlotResponse.Data) {
                if (position > 0) {
                    timeSlotTimeAdapter.selected = -1
                    deliveryInfoViewModel.fisrtSelected = false
                    deliveryInfoViewModel.selected = true
                }
                timeList = Data.slots as ArrayList<TimeSlotResponse.Data.Slot>
                timeSlotTimeAdapter.setList(timeList, position)

                timeSlotTimeAdapter.notifyDataSetChanged()

                sloteId = timeList.firstOrNull { it.selectedTime == true }?.id
            }

            override fun isTimeSlotTimeClick(
                position: Int,
                Data: TimeSlotResponse.Data.Slot,
                parentPos: Int
            ) {

            }
        }, dateList)
        getDataBinding().rvDeliveryTimeSlot.adapter = timeSlotAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapterTime() {
        timeSlotTimeAdapter = TimeSlotTimeAdapter(requireContext(), object : TimeSlotInterface {
            override fun isTimeSlotClick(position: Int, Data: TimeSlotResponse.Data) {

            }

            override fun isTimeSlotTimeClick(
                position: Int,
                Data: TimeSlotResponse.Data.Slot,
                parentPos: Int
            ) {
                timeList = dateList[parentPos].slots as ArrayList<TimeSlotResponse.Data.Slot>
                timeList.firstOrNull { it.id == Data.id }?.selectedTime = true
                timeList.firstOrNull { it.id != Data.id }?.selectedTime = false
                timeSlotTimeAdapter.setList(timeList, parentPos)
                timeSlotTimeAdapter.notifyDataSetChanged()
                sloteId = timeList.firstOrNull { it.selectedTime == true }?.id
            }
        }, timeList)
        getDataBinding().rvDeliveryTimeSlotDay.adapter = timeSlotTimeAdapter
    }

    private fun setLiveDataObservers() {

        deliveryInfoViewModel.deliveryInfoResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    deliveryInfoViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    deliveryInfoViewModel.showProgressBar(false)
                    state.message.let { deliveryInfoViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<DeliveryInfoResponce> -> {
                    deliveryInfoViewModel.showProgressBar(false)

                    if (state.response?.message.equals("Success")){
                        findNavController().navigate(TimeSlotFragmentDirections.actionTimeSlotFragmentToReviewPaymentFragment())
                    }else{
                        Toast.makeText(requireContext(), state.response?.message, Toast.LENGTH_SHORT).show()
                    }

                }

            }
        }

        deliveryInfoViewModel.timeSlotResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    deliveryInfoViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    deliveryInfoViewModel.showProgressBar(false)
                    state.message.let { deliveryInfoViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<TimeSlotResponse> -> {
                    deliveryInfoViewModel.showProgressBar(false)
                    if (state.response?.status == 200) {

                        sloteId= state.response.data[0].slots[0].id

                        dateList.clear()
                        dateList.addAll(state.response.data as ArrayList<TimeSlotResponse.Data>)


                        timeList.clear()
                        timeList.addAll(state.response.data[0].slots as ArrayList<TimeSlotResponse.Data.Slot>)


                        if (timeList.size > 0) timeList[0].selectedTime = true

                        setAdapterDay()
                        setAdapterTime()

                    }
                }

            }
        }
    }


}