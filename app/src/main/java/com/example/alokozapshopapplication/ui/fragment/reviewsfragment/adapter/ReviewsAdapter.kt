package com.example.alokozapshopapplication.ui.fragment.reviewsfragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alokozapshopapplication.databinding.RecyclarViewReviewListBinding
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.model.ProductDetailsResponse


class ReviewsAdapter(

    private val context: Context,
    private var reviewsData:ArrayList<ProductDetailsResponse.Review?>,
    private var reviewsRatingData:ArrayList<ProductDetailsResponse.Review.Rating>
):
    RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {

    private lateinit var binding:RecyclarViewReviewListBinding


    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ReviewViewHolder {
        binding =
            RecyclarViewReviewListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder:ReviewViewHolder, position: Int) {
        binding.reviewListModel =reviewsData[position]


    }

    override fun getItemCount(): Int {
        Log.e("Tag", reviewsData.size.toString())
        return reviewsData.size

    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateRatingData(reviewsData: ArrayList<ProductDetailsResponse.Review?>) {
        this.reviewsData = reviewsData
        this.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateRatingReviewData(reviewsRatingData: ArrayList<ProductDetailsResponse.Review.Rating>) {
        this.reviewsRatingData = reviewsRatingData
        this.notifyDataSetChanged()
    }


}