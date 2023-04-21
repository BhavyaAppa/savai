package com.example.alokozapshopapplication.ui.fragment.languagefragment.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.RecyckarViewCountryListingBinding
import com.example.alokozapshopapplication.databinding.RecyclarViewLanguageListingBinding
import com.example.alokozapshopapplication.ui.fragment.countryfragment.`interface`.CountryListInterface
import com.example.alokozapshopapplication.ui.fragment.countryfragment.model.CountryResponse
import com.example.alokozapshopapplication.ui.fragment.currencyfragment.adapter.CurrencyListingAdapter
import com.example.alokozapshopapplication.ui.fragment.languagefragment.`interface`.LanguageListInterface
import com.example.alokozapshopapplication.ui.fragment.languagefragment.model.LanguageResponce


class LanguageListingAdapter(
    private val context: Context,

    private var languageList: ArrayList<LanguageResponce.LanguageData>,
    private val interFace: LanguageListInterface
) :
    RecyclerView.Adapter<LanguageListingAdapter.CountryListingViewHolder>() {

    private lateinit var binding: RecyclarViewLanguageListingBinding



    @SuppressLint("NotifyDataSetChanged")
    fun setList(countryList: ArrayList<LanguageResponce.LanguageData>) {
        this.languageList = countryList
        this.notifyDataSetChanged()
    }

    inner class CountryListingViewHolder(val binding: RecyclarViewLanguageListingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(position: Int) {
            binding.languageListingModel = languageList[position]

            binding.tvEnglish.isSelected = languageList[position].isSelected
            binding.root.setOnClickListener {
                interFace.isItemClick(position, languageList[position])
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
            RecyclarViewLanguageListingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CountryListingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryListingViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return languageList.size
    }


}