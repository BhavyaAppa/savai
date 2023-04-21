package com.example.alokozapshopapplication.ui.fragment.reviewpayment.repository

import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.reviewpayment.model.ReviewPaymentResponse
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReviewPaymentRepository(val apiInterface: ApiInterface) : BaseRepository() {

    suspend fun getReviewAndPayment(
        customerToken: String,
        quoteId: String
    ): ResponseHandler<ReviewPaymentResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getReviewAndPayment(
                        customerToken = customerToken,
                        quoteId = quoteId
                    )
                })
        }
    }


}
