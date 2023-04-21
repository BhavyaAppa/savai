package com.example.alokozapshopapplication.ui.fragment.contactus.contactusviewmodel


import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.contactus.contactus.ContactUsRequest
import com.example.alokozapshopapplication.ui.fragment.contactus.contactus.ContactUsResponse
import com.example.alokozapshopapplication.ui.fragment.contactus.contactusrepository.ContactUsRepository
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.Validation
import kotlinx.coroutines.launch


class ContactUsViewModel: ViewModelBase() {
    var contactUsRequestData: ContactUsRequest? = null
    var contactUsResponceLiveData = MutableLiveData<ResponseHandler<ContactUsResponse>>()

    var contactUsRepository: ContactUsRepository? =
        ContactUsRepository(ApiClient.getApiInterface())

    init {
        contactUsRequestData = ContactUsRequest()
    }

    fun onSubmitClick() {
        when {
            Validation.isFieldEmpty(contactUsRequestData?.name.toString().trim()) -> {
                showSnackbarMessage("Please enter first name")
            }
            Validation.isNameValidate(contactUsRequestData?.name.toString().trim()) -> {
                showSnackbarMessage("first name should be minimum 2 and maximum 100 characters")
            }
            Validation.isFieldEmpty(contactUsRequestData?.email.toString().trim()) -> {
                showSnackbarMessage("Please enter email-id")
            }
            Validation.isEmailValid(contactUsRequestData?.email.toString().trim()) -> {
                showSnackbarMessage("valid Email required")
            }
            Validation.isFieldEmpty(contactUsRequestData?.telephone.toString().trim()) -> {
                showSnackbarMessage("Please enter mobile number")
            }
            Validation.isMobileValidation(contactUsRequestData?.telephone.toString().trim()) -> {
                showSnackbarMessage("Valid Mobile required")
            }
            Validation.isMobileValidate(contactUsRequestData?.telephone.toString().trim()) -> {
                showSnackbarMessage("Please enter minimum 8 and maximum 14 digit mobile number ")
            }
            Validation.isFieldEmpty(contactUsRequestData?.comment.toString().trim()) -> {
                showSnackbarMessage("Please enter comment")
            }
            else -> {
                getContactUs()

            }
        }
    }



    fun getContactUs() {
        viewModelScope.launch(coroutineContext) {
            contactUsResponceLiveData.value = ResponseHandler.Loading
            contactUsResponceLiveData.value = contactUsRepository?.getContactUs(contactUsRequestData!!)
        }

    }
}