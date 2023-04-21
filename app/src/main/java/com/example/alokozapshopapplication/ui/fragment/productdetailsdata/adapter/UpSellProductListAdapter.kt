package com.example.alokozapshopapplication.ui.fragment.productdetailsdata.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alokozapshopapplication.databinding.ProductUpsellListRecylarviewBinding
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.`interface`.ProductDetailsFragmentInterface
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.model.ProductDetailsResponse


class UpSellProductListAdapter(

    private val context: Context,
    private val listener: ProductDetailsFragmentInterface,
    private var upSellProductList:ArrayList<ProductDetailsResponse.UpsellProduct>
):
    RecyclerView.Adapter<UpSellProductListAdapter.UpSellProductListProductListViewHolder>() {

    private lateinit var binding: ProductUpsellListRecylarviewBinding


    class UpSellProductListProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):UpSellProductListProductListViewHolder {
        binding =
            ProductUpsellListRecylarviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpSellProductListProductListViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder:UpSellProductListProductListViewHolder, position: Int) {
        binding.productListResponce =upSellProductList[position]

        binding.root.setOnClickListener{
            listener.onProductUpSell(position,it,upSellProductList[position])
        }


        Glide.with(context)
            .load(upSellProductList[position].thumbNail)
            .centerCrop()
            .thumbnail(Glide.with(context).load("https://raw.githubusercontent.com/ybq/Android-SpinKit/master/art/Pulse.gif"))
            .into(binding.ivSubCategory)

    }

    override fun getItemCount(): Int {
        Log.e("Tag", upSellProductList.size.toString())
        return upSellProductList.size

    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategoryItemData(list: ArrayList<ProductDetailsResponse.UpsellProduct>) {
        this.upSellProductList = list
        this.notifyDataSetChanged()
    }



}
