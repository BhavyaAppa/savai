package com.example.alokozapshopapplication.ui.fragment.homefragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.RecyclarViewHomeSubCategoryItemBinding
import com.example.alokozapshopapplication.ui.fragment.homefragment.`interface`.HomeFragmentItemClickInterface

import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.utils.RecyclarItemClickInterface

class ParentSubCategoryAdapter(
    private val context: Context,
    private val listener: HomeFragmentItemClickInterface,
    private var categoryData: ArrayList<HomeResponse.Carousel>,

    private  var subCategoryData: ArrayList<HomeResponse.Carousel.Product?>
) : RecyclerView.Adapter<ParentSubCategoryAdapter.CategoryViewHolder>(){


    private lateinit var binding:RecyclarViewHomeSubCategoryItemBinding

    private var bannerImageList=java.util.ArrayList<HomeResponse.BannerImage>()



    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        binding =
            RecyclarViewHomeSubCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParentSubCategoryAdapter.CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        binding.homeSubCategoryModel =categoryData[position]

        val child = categoryData[position].productList?.let { ArrayList(it) }
        binding.rvSubCategoryItem.adapter= child?.let {
            ChildSubCategoryAdapter(context,listener,
                it
            )
        }

        if(categoryData[position].label?.contains("Home Page Banner (UAE English)") == true){
            binding.tvCategoryItem.visibility=View.GONE
            binding.tvCategoryViewAll.visibility=View.GONE
        }


        binding.rvSubCategoryItem.layoutManager=
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL,false)

        binding.tvCategoryViewAll.setOnClickListener{
            listener.onItemClick(position,it,categoryData[position])
        }

    }

    override fun getItemCount(): Int {
        Log.e("Tag", categoryData.size.toString())
        return categoryData.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategoryItemData(list: ArrayList<HomeResponse.Carousel>) {
        this.categoryData = list
        this.notifyDataSetChanged()
    }

}