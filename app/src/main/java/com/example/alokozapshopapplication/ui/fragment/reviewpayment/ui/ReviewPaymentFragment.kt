package com.example.alokozapshopapplication.ui.fragment.reviewpayment.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentReviewPaymentBinding

import com.example.alokozapshopapplication.ui.base.FragmentBase

import com.example.alokozapshopapplication.ui.fragment.reviewpayment.model.ReviewPaymentResponse
import com.example.alokozapshopapplication.ui.fragment.reviewpayment.viewmodel.ReviewPaymentViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey


class ReviewPaymentFragment : FragmentBase<ReviewPaymentViewModel, FragmentReviewPaymentBinding>(){
    private lateinit var reviewPaymentViewModel: ReviewPaymentViewModel
    val customerToken = MyPreference.getValueString(PrefKey.ACCESS_TOKEN, "")
    val quoteId = MyPreference.getValueString(PrefKey.QUOTE_ID, "")



    override fun getLayoutId(): Int {
        return R.layout.fragment_review_payment
    }

    override fun setupToolbar() {
    }


    override fun initializeScreenVariables() {
        getDataBinding().lifecycleOwner = viewLifecycleOwner

        reviewPaymentViewModel.getReviewAndPayment(customerToken=customerToken.toString(),quoteId=quoteId.toString())

        getDataBinding().btnHome.setOnClickListener {
            val action=ReviewPaymentFragmentDirections.actionReviewPaymentFragmentToMnHome()
            findNavController().navigate(action)
        }

        setLiveDataObservers()
    }

    override fun getViewModel(): ReviewPaymentViewModel {
        reviewPaymentViewModel = ViewModelProvider(this)[ReviewPaymentViewModel::class.java]
        return reviewPaymentViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }

    private fun setLiveDataObservers() {

        reviewPaymentViewModel.reviewPaymentResponseLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    reviewPaymentViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    reviewPaymentViewModel.showProgressBar(false)
                    state.message.let { reviewPaymentViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<ReviewPaymentResponse> -> {
                    reviewPaymentViewModel.showProgressBar(false)
                    getDataBinding().reviewPaymentViewModel=state.response
                }
            }
        }


    }


}