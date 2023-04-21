package com.example.alokozapshopapplication.ui.fragment.currencyfragment.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alokozapshopapplication.databinding.RecyclarViewCurrencyListingBinding
import com.example.alokozapshopapplication.ui.fragment.currencyfragment.`interface`.CurrencyListInterface
import com.example.alokozapshopapplication.ui.fragment.currencyfragment.model.CurrencyResponse


class CurrencyListingAdapter(
    private val context: Context,

    private var currencyList: ArrayList<CurrencyResponse.AllowedCurrency>,
    private val interFace: CurrencyListInterface
) :
    RecyclerView.Adapter<CurrencyListingAdapter.CurrencyListingViewHolder>() {

    private lateinit var binding: RecyclarViewCurrencyListingBinding



    @SuppressLint("NotifyDataSetChanged")
    fun setList(currencyList: ArrayList<CurrencyResponse.AllowedCurrency>) {
        this.currencyList = currencyList
        this.notifyDataSetChanged()
    }

    inner class CurrencyListingViewHolder(val binding: RecyclarViewCurrencyListingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(position: Int) {
            binding.currencyListingModel = currencyList[position]
            binding.tvAed.isSelected = currencyList[position].isSelected
            binding.root.setOnClickListener {
                interFace.isItemClick(position, currencyList[position])
            }
        }


    }

    override fun getItemId(position: Int): Long {
        return getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyListingViewHolder {
        binding =
            RecyclarViewCurrencyListingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CurrencyListingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyListingViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }


}