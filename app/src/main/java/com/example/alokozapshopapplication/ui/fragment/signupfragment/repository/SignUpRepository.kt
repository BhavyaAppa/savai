package com.example.alokozapshopapplication.ui.fragment.signupfragment.repository

import com.example.alokozapshopapplication.ui.api.ApiRetrofitObject
import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.newsmedia.model.NewsMediaResponce
import com.example.alokozapshopapplication.ui.fragment.signupfragment.signupmodel.SignUpRequest
import com.example.alokozapshopapplication.ui.fragment.signupfragment.signupmodel.SignUpResponce
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class SignUpRepository(val apiInterface: ApiInterface) : BaseRepository() {


    suspend fun userSignUp(signUpRequest: SignUpRequest): ResponseHandler<SignUpResponce> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.userSignUp(

                        signUpRequest.firstName.toString(),
                        signUpRequest.lastName.toString(),
                        signUpRequest.mobileNumber.toString(),
                        signUpRequest.email.toString(),
                        signUpRequest.password.toString(),
                        signUpRequest.confirmPassword.toString(),

                    )
                })
        }
    }




/*
    suspend fun userSignUp(
        firstName: String,
        lastName: String,
        mobileNumber: String,
        email: String,
        password: String,
        confirmPassword: String

    ): Response<SignUpResponce> {
        return ApiRetrofitObject.api.userSignUp(
            firstName,
            lastName,
            mobileNumber,
            email,
            password,
            confirmPassword
        )
    }
*/

}