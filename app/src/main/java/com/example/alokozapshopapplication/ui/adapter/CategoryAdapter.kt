package com.example.alokozapshopapplication.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.CategoryNewLayoutBinding
import com.example.alokozapshopapplication.databinding.RecyclarViewCategoriesItemBinding
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.`interface`.CategoryItemInterFace
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.model.CategoryItemResponse
import com.example.alokozapshopapplication.ui.utils.RecyclarItemClickInterface
import com.squareup.picasso.Picasso


class CategoryAdapter(

    private val context: Context,
    private val listener: CategoryItemInterFace,
    private var categoryData: ArrayList<CategoryItemResponse.Category>
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryItemViewHolder>() {


    private lateinit var binding: CategoryNewLayoutBinding


    class CategoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        binding =
            CategoryNewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryItemViewHolder(binding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {

        binding.categoryItemModel =categoryData[position]

        binding.root.setOnClickListener{
            listener.onClick(position,it,categoryData[position])
        }



        Glide.with(context)
            .load(categoryData[position].url)
            .thumbnail(Glide.with(context).load("https://raw.githubusercontent.com/ybq/Android-SpinKit/master/art/Pulse.gif"))
            .centerCrop()
            .into(binding.ivItemImage)


/*        Picasso.get()
            .load(categoryData[position].url)
            .fit().centerCrop()
            .error(R.drawable.ic_alokozap_app_logo)
            .placeholder(R.drawable.ic_alokozap_app_logo)
            .into(binding.ivItemImage);*/

    }

    override fun getItemCount(): Int {
        Log.e("Tag", categoryData.size.toString())
        return categoryData.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategoryItemData(list: ArrayList<CategoryItemResponse.Category>) {
        this.categoryData = list
        this.notifyDataSetChanged()
    }
}