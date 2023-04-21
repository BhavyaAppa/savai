package com.example.alokozapshopapplication.ui.fragment.searchfragment.adapter

import android.annotation.SuppressLint
import android.content.Context

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.RecyclarViewProductDetailsBinding
import com.example.alokozapshopapplication.ui.fragment.searchfragment.SearchListItemInterface
import com.example.alokozapshopapplication.ui.fragment.searchfragment.model.searchmodel.SearchDataRespoance

class ProductListAdapter(
    private val context: Context,
    private val listener: SearchListItemInterface,
    private var productItemList: ArrayList<SearchDataRespoance.Indice.Item>
) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {


    class ProductListViewHolder(val binding:RecyclarViewProductDetailsBinding ) :
        RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListAdapter.ProductListViewHolder(
            RecyclarViewProductDetailsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.binding.productDetailsResponse = productItemList[position]

        holder.binding.root.setOnClickListener {
            productItemList[position].let { it1 ->
                listener.onProductItemClick(position, it, it1)
            }
        }

        holder.binding.ivProductImage.let {
            Glide.with(context)
                .load(productItemList[position].image)
                .placeholder(R.drawable.ic_alokozap_app_logo)
                .centerCrop()
                .error(R.drawable.ic_alokozap_app_logo)
                .into(it)
        }


    }

    override fun getItemCount(): Int {
        return productItemList.size
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateProductItemList(list:ArrayList<SearchDataRespoance.Indice.Item>) {
        this.productItemList = list
        this.notifyDataSetChanged()
    }

}
