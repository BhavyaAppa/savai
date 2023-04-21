package com.example.alokozapshopapplication.ui.fragment.searchfragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.ChildRecyclarViewSubCategoryBinding
import com.example.alokozapshopapplication.databinding.RecyclarViewChildItemProductCategoryBinding
import com.example.alokozapshopapplication.databinding.RecyclarViewChildItemProductListBinding
import com.example.alokozapshopapplication.ui.fragment.homefragment.`interface`.HomeFragmentItemClickInterface
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.searchfragment.SearchListItemInterface
import com.example.alokozapshopapplication.ui.fragment.searchfragment.model.searchmodel.SearchDataRespoance


class ChildProductListAdapter(
    private val context: Context,
    private val listener: SearchListItemInterface,
    private var productListCountData: java.util.ArrayList<SearchDataRespoance.Indice.Item>
) : RecyclerView.Adapter<ChildProductListAdapter.ProductListCountViewHolder>() {


    class ProductListCountViewHolder(val binding: RecyclarViewChildItemProductListBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListCountViewHolder {
        return ProductListCountViewHolder(
            RecyclarViewChildItemProductListBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductListCountViewHolder, position: Int) {
        holder.binding.popularSuggestionChildResponse = productListCountData[position]
        holder.binding.root.setOnClickListener {
            productListCountData[position].let { it1 ->
                listener.onProductClick(position, it, it1)
            }
        }


    }

    override fun getItemCount(): Int {
        Log.e("Tag", productListCountData.size.toString())
        return productListCountData.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategoryItemData(list: java.util.ArrayList<SearchDataRespoance.Indice.Item>) {
        this.productListCountData = list
        this.notifyDataSetChanged()
    }


}