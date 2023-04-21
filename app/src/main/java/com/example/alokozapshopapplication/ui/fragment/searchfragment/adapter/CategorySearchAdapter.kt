package com.example.alokozapshopapplication.ui.fragment.searchfragment.adapter

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
import com.example.alokozapshopapplication.databinding.RecyclarViewSearchCategoryBinding
import com.example.alokozapshopapplication.ui.fragment.homefragment.`interface`.HomeFragmentItemClickInterface
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.searchfragment.`interface`.SearchCategoryInterface
import com.example.alokozapshopapplication.ui.utils.RecyclarItemClickInterface

class CategorySearchAdapter(

    private val context: Context,
    private val listener: SearchCategoryInterface,
    private var categoryData:ArrayList<HomeResponse.FeaturedCategory>
):
    RecyclerView.Adapter<CategorySearchAdapter.CategoryViewHolder>() {

    private lateinit var binding: RecyclarViewSearchCategoryBinding


    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):CategoryViewHolder {
        binding =
            RecyclarViewSearchCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder:CategoryViewHolder, position: Int) {
        binding.homeCategoryItemModel =categoryData[position]

        binding.cvCardItem.setOnClickListener{
            listener.onClickCategory(position,it,categoryData[position])
        }


        Glide.with(context)
            .load(categoryData[position].url)
            .placeholder(R.drawable.ic_alokozap_app_logo)
            .centerCrop()
            .error(R.drawable.ic_alokozap_app_logo)
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