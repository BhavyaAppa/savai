package com.example.alokozapshopapplication.ui.fragment.homefragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.RecyclarViewHomeCategoryBinding
import com.example.alokozapshopapplication.ui.fragment.homefragment.`interface`.HomeFragmentItemClickInterface
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.utils.RecyclarItemClickInterface

class CategoryAdapter(

    private val context: Context,
    private val listener: HomeFragmentItemClickInterface,
    private var categoryData:ArrayList<HomeResponse.FeaturedCategory>
):
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private lateinit var binding: RecyclarViewHomeCategoryBinding


    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):CategoryViewHolder {
        binding =
            RecyclarViewHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder:CategoryViewHolder, position: Int) {
        binding.homeCategoryItemModel =categoryData[position]

        binding.cvCardItem.setOnClickListener{
            listener.onClickFeaturedCategory(position,it,categoryData[position])
        }


        Glide.with(context)
            .load(categoryData[position].url)
            .centerCrop()
            .thumbnail(Glide.with(context).load("https://raw.githubusercontent.com/ybq/Android-SpinKit/master/art/Pulse.gif"))
            .into(binding.ivItemImage)

    }

    override fun getItemCount(): Int {
        Log.e("Tag", categoryData.size.toString())
        return categoryData.size

    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategoryItemData(list: ArrayList<HomeResponse.FeaturedCategory>) {
        this.categoryData = list
        this.notifyDataSetChanged()
    }


}