package com.example.alokozapshopapplication.ui.fragment.reviewsfragment.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.NavGraphDirections
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentReviewsBinding
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.model.ProductDetailsResponse
import com.example.alokozapshopapplication.ui.fragment.reviewsfragment.adapter.ReviewsAdapter

class ReviewsFragment() : FragmentBase<ViewModelBase, FragmentReviewsBinding>() {
    private lateinit var viewModelBase: ViewModelBase


    constructor(reviewModel: ProductDetailsResponse?) : this() {
        this.reviewModel = reviewModel
    }

    private var reviewModel: ProductDetailsResponse? = null
    private lateinit var reviewsAdapter: ReviewsAdapter
    private var reviewsData: ArrayList<ProductDetailsResponse.Review?> = ArrayList()
    private var reviewsRatingData = ArrayList<ProductDetailsResponse.Review.Rating>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reviewModel?.reviewList?.let { reviewsData.addAll(it) }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_reviews
    }

    override fun setupToolbar() {

    }

    override fun initializeScreenVariables() {
        getDataBinding().lifecycleOwner = viewLifecycleOwner
        getDataBinding().reviewModel = reviewModel

        if (reviewModel?.reviewCount == 0){
            getDataBinding().lrLayout.visibility=View.GONE
            getDataBinding().rvRecyclarView.visibility=View.GONE

        }


        getDataBinding().btnReviews.setOnClickListener {
            val action= NavGraphDirections.actionToWriteReviewFragment()
            findNavController().navigate(action)

        }
        addToReviewAdapter()
    }

    override fun getViewModel(): ViewModelBase? {
        viewModelBase = ViewModelProvider(this)[ViewModelBase::class.java]
        return viewModelBase
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {

        showSnackbar(message.toString())
    }

    private fun addToReviewAdapter() {
        reviewsData.let {
            reviewsAdapter = ReviewsAdapter(requireContext(), it,reviewsRatingData)
            reviewsAdapter.updateRatingData(reviewsData)
            reviewsAdapter.updateRatingReviewData(reviewsRatingData)
            getDataBinding().rvRecyclarView.adapter = reviewsAdapter
        }
    }


}







