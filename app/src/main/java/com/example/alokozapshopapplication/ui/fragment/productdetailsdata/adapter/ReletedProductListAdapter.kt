package com.example.alokozapshopapplication.ui.fragment.productdetailsdata.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.ProductListRecentCategoryReccylarviewBinding
import com.example.alokozapshopapplication.databinding.RecyclarViewHomeCategoryBinding
import com.example.alokozapshopapplication.ui.fragment.homefragment.`interface`.HomeFragmentItemClickInterface
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.`interface`.ProductDetailsFragmentInterface
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.model.ProductDetailsResponse
import com.example.alokozapshopapplication.ui.utils.RecyclarItemClickInterface

class ReletedProductListAdapter(

    private val context: Context,
    private val listener: ProductDetailsFragmentInterface,
    private var reletedProductList:ArrayList<ProductDetailsResponse.RelatedProduct>
):
    RecyclerView.Adapter<ReletedProductListAdapter.ReletedProductListViewHolder>() {

    private lateinit var binding: ProductListRecentCategoryReccylarviewBinding


    class ReletedProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ReletedProductListViewHolder {
        binding =
            ProductListRecentCategoryReccylarviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReletedProductListViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder:ReletedProductListViewHolder, position: Int) {
        binding.productListResponce =reletedProductList[position]

        binding.root.setOnClickListener{
            listener.onProductRecent(position,it,reletedProductList[position])
        }


        Glide.with(context)
            .load(reletedProductList[position].thumbNail)
            .centerCrop()
            .thumbnail(Glide.with(context).load("https://raw.githubusercontent.com/ybq/Android-SpinKit/master/art/Pulse.gif"))
            .into(binding.ivSubCategory)

    }

    override fun getItemCount(): Int {
        Log.e("Tag", reletedProductList.size.toString())
        return reletedProductList.size

    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategoryItemData(list: ArrayList<ProductDetailsResponse.RelatedProduct>) {
        this.reletedProductList = list
        this.notifyDataSetChanged()
    }


}