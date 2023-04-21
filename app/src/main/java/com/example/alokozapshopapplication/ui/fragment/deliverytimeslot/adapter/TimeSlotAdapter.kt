package com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.RecyclarViewDeliverySlotBinding
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.`interface`.TimeSlotInterface
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.timeslotmodel.TimeSlotResponse


class TimeSlotAdapter(
    private val context: Context,
    private var timeSlotClick:TimeSlotInterface,
    private var dateList: ArrayList<TimeSlotResponse.Data>
) :
    RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder>() {

    private lateinit var binding: RecyclarViewDeliverySlotBinding
    var selected = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setList(dateList: ArrayList<TimeSlotResponse.Data>) {
        this.dateList = dateList
        this.notifyDataSetChanged()
    }

    inner class TimeSlotViewHolder(val binding: RecyclarViewDeliverySlotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(position: Int) {
            binding.timeslotModel = dateList[position]
            binding.root.setOnClickListener {
                selected = position
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotViewHolder {
        binding =
            RecyclarViewDeliverySlotBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return TimeSlotViewHolder(binding)
    }

    override fun getItemCount(): Int = dateList.size


    override fun onBindViewHolder(holder: TimeSlotViewHolder, position: Int) {
        holder.bind(position)
        if (selected == position) {
            timeSlotClick.isTimeSlotClick(position,dateList[position])

            holder.binding.clDeliveryDate.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.purple_dark_main
                )
            )
            holder.binding.tvDeliveryDate.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )

            holder.binding.tvDeliveryDay.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )

        }else{
            holder.binding.clDeliveryDate.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )
            holder.binding.tvDeliveryDate.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.purple_dark_main
                )
            )

            holder.binding.tvDeliveryDay.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.white_gray
                )
            )

        }
    }
}