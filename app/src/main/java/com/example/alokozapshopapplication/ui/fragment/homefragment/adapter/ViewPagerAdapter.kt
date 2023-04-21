package com.example.alokozapshopapplication.ui.fragment.homefragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.RecylarViewSubCategoryItemBinding
import com.example.alokozapshopapplication.databinding.ViewpagerSliderImageBinding
import com.example.alokozapshopapplication.ui.adapter.CategorySubAdapter
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.CategoryItemListResponse


class ViewPagerAdapter(
    private val context: Context,
    private var bannerImageList: java.util.ArrayList<HomeResponse.Carousel.Banner>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    class ViewPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    private lateinit var binding: ViewpagerSliderImageBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        binding =
            ViewpagerSliderImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerAdapter.ViewPagerHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        binding.bannerImageListModel = bannerImageList[position]


        Glide.with(context)
            .load(bannerImageList[position].url)
            .thumbnail(Glide.with(context).load("https://raw.githubusercontent.com/ybq/Android-SpinKit/master/art/Pulse.gif"))
            .centerCrop()
            .into(binding.ivSliderImage)
    }

    override fun getItemCount(): Int {
        return bannerImageList.size

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateCategoryItemData(list: java.util.ArrayList<HomeResponse.Carousel.Banner>) {
        this.bannerImageList = list
        this.notifyDataSetChanged()
    }

}



