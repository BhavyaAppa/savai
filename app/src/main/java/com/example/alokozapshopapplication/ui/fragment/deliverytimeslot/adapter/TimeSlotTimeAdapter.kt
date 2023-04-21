package com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.RecyclarViewDeliverySlotDayBinding
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.`interface`.TimeSlotInterface
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.timeslotmodel.TimeSlotResponse


class TimeSlotTimeAdapter(
    private val context: Context,
    private var timeSlotTimeClick: TimeSlotInterface,
    private var timeList : ArrayList<TimeSlotResponse.Data.Slot>
) :
    RecyclerView.Adapter<TimeSlotTimeAdapter.TimeSlotTimeViewHolder>() {

    private lateinit var binding: RecyclarViewDeliverySlotDayBinding
    var selected = 0
    var parentPos = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setList(timeList: ArrayList<TimeSlotResponse.Data.Slot>, parentPos: Int = 0) {
        this.timeList = timeList
        this.parentPos = parentPos
        this.notifyDataSetChanged()

    }


    inner class TimeSlotTimeViewHolder(val binding: RecyclarViewDeliverySlotDayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(position: Int) {
            binding.timeslotTimeModel = timeList[position]
            if (binding.timeslotTimeModel?.selectedTime == true) {

                binding.clDeliveryDay.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.purple_dark_main
                    )
                )
                binding.tvDeliveryTime.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.white
                    )
                )

            } else {
                binding.clDeliveryDay.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.white
                    )
                )
                binding.tvDeliveryTime.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.purple_dark_main
                    )
                )
            }
            binding.root.setOnClickListener {
                timeSlotTimeClick.isTimeSlotTimeClick(position, timeList[position],parentPos)
                //notifyDataSetChanged()
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotTimeViewHolder {
        binding =
            RecyclarViewDeliverySlotDayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return TimeSlotTimeViewHolder(binding)
    }

    override fun getItemCount(): Int = timeList.size


    override fun onBindViewHolder(holder: TimeSlotTimeViewHolder, position: Int) {
        holder.bind(position)


    }

}