package com.example.alokozapshopapplication.ui.fragment.countryfragment.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.RecyckarViewCountryListingBinding
import com.example.alokozapshopapplication.ui.fragment.countryfragment.`interface`.CountryListInterface
import com.example.alokozapshopapplication.ui.fragment.countryfragment.model.CountryResponse



class CountryListingAdapter(
    private val context: Context,

    private var countryList: ArrayList<CountryResponse.CountryData>,
    private val interFace: CountryListInterface
) :
    RecyclerView.Adapter<CountryListingAdapter.CountryListingViewHolder>() {

    private lateinit var binding: RecyckarViewCountryListingBinding



    @SuppressLint("NotifyDataSetChanged")
    fun setList(countryList: ArrayList<CountryResponse.CountryData>) {
        this.countryList = countryList
        this.notifyDataSetChanged()
    }

    inner class CountryListingViewHolder(val binding: RecyckarViewCountryListingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(position: Int) {
            binding.countryListingModel = countryList[position]
            binding.tvUae.isSelected = countryList[position].isSelected
            binding.root.setOnClickListener {
                interFace.isItemClick(position, countryList[position])
            }
        }


    }

    override fun getItemId(position: Int): Long {
        return getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListingViewHolder {
        binding =
            RecyckarViewCountryListingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CountryListingViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CountryListingViewHolder, position: Int) {

        holder.bind(position)

        Glide.with(context)
            .load(countryList[position].countryFlag)
            .placeholder(R.drawable.ic_alokozap_app_logo)
            .fitCenter()
            .error(R.drawable.ic_alokozap_app_logo)
            .into(holder.binding.ivUae)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }


}