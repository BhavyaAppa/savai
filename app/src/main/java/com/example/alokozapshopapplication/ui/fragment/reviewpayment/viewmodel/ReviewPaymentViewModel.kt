package com.example.alokozapshopapplication.ui.fragment.reviewpayment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.reviewpayment.model.ReviewPaymentResponse
import com.example.alokozapshopapplication.ui.fragment.reviewpayment.repository.ReviewPaymentRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.launch

class ReviewPaymentViewModel : ViewModelBase() {

    var reviewPaymentResponseLiveData = MutableLiveData<ResponseHandler<ReviewPaymentResponse>>()
    var reviewPaymentRepository: ReviewPaymentRepository? =
        ReviewPaymentRepository(ApiClient.getApiInterface())

    fun getReviewAndPayment(customerToken: String, quoteId: String) {
        viewModelScope.launch(coroutineContext) {
            reviewPaymentResponseLiveData.value = ResponseHandler.Loading
            reviewPaymentResponseLiveData.value = reviewPaymentRepository?.getReviewAndPayment(customerToken, quoteId)
        }

    }


}
