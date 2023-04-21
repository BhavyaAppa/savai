package com.example.alokozapshopapplication.ui.fragment.searchfragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alokozapshopapplication.databinding.RecyclarViewChildItemProductCategoryBinding
import com.example.alokozapshopapplication.databinding.RecyclarViewChildItemProductListBinding
import com.example.alokozapshopapplication.ui.fragment.searchfragment.SearchListItemInterface
import com.example.alokozapshopapplication.ui.fragment.searchfragment.model.searchmodel.SearchDataRespoance


class ChildProductCategoryAdapter(
    private val context: Context,
    private val listener: SearchListItemInterface,
    private var productListCatData: java.util.ArrayList<SearchDataRespoance.Indice.Item>
) : RecyclerView.Adapter<ChildProductCategoryAdapter.ChildProductCategoryViewHolder>() {


    class ChildProductCategoryViewHolder(val binding: RecyclarViewChildItemProductCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildProductCategoryViewHolder {
        return ChildProductCategoryViewHolder(
            RecyclarViewChildItemProductCategoryBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ChildProductCategoryViewHolder, position: Int) {
        holder.binding.productInCatChildResponse = productListCatData[position]

        holder.binding.root.setOnClickListener {
            productListCatData[position].let { it1 ->
                listener.onProductClick(position, it, it1)
            }
        }


    }

    override fun getItemCount(): Int {
        Log.e("Tag", productListCatData.size.toString())
        return productListCatData.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategoryItemData(list: java.util.ArrayList<SearchDataRespoance.Indice.Item>) {
        this.productListCatData = list
        this.notifyDataSetChanged()
    }


}