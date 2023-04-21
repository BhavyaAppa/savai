package com.example.alokozapshopapplication.ui.fragment.forgotpassword.forgotviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.forgotpassword.forgotrepository.ForgotRepository
import com.example.alokozapshopapplication.ui.utils.Validation

class ForgotViewModel: ViewModelBase() {


    val forgotClick = MutableLiveData<Boolean>()


    var mobileNo = MutableLiveData<String>()
    var email = MutableLiveData<String>()



    fun forgotPasswordValidation() {

        when {
            Validation.isFieldEmpty(mobileNo.value.toString().trim()) &&
                    Validation.isFieldEmpty(email.value.toString().trim()) -> {
                showSnackbarMessage("Please enter email-id or mobile no")
            }
            Validation.isFieldEmpty(mobileNo.value.toString().trim()) &&
                    Validation.isEmailValid(email.value.toString().trim()) -> {
                showSnackbarMessage("valid Email required")
            }
            Validation.isFieldEmpty(email.value.toString().trim()) &&
                    Validation.isMobileValidation(mobileNo.value.toString().trim()) -> {
                showSnackbarMessage("Valid Mobile required")
            }
            Validation.isFieldEmpty(email.value.toString().trim()) &&
                    Validation.isMobileValidate(mobileNo.value.toString().trim()) -> {
                showSnackbarMessage("Please enter minimum 8 and maximum 14 digit mobile number ")
            }
            else -> {
                forgotClick.value = true
            }
        }
    }

/*    fun forgotPasswordValidation() {

        if (Validation.isFieldEmpty(mobileNo.value.toString().trim()) &&
            Validation.isFieldEmpty(email.value.toString().trim())
        ) {
            showErrorMsg("Please enter mobile number/email-id")
        } else if (Validation.isFieldEmpty(mobileNo.value.toString().trim()) &&
            Validation.isEmailValid(email.value.toString().trim())
        ) {
            showErrorMsg("valid Email required")
        } else if (Validation.isFieldEmpty(email.value.toString().trim()) &&
            Validation.isMobileValidation(mobileNo.value.toString().trim())
        ) {
            showErrorMsg("Valid Mobile required")
        } else if (Validation.isFieldEmpty(email.value.toString().trim()) &&
            Validation.isMobileValidate(mobileNo.value.toString().trim())
        ) {
            showErrorMsg("Please enter minimum 8 and maximum 14 digit mobile number ")
        } else {
            forgotClick.value = true
        }
    }*/

/*    fun forgotPasswordValidation() {

        when {
            Validation.isFieldEmpty(mobileNo.value.toString().trim()) -> {
                showErrorMsg("Please enter mobile number/email-id")

            }
            Validation.isFieldEmpty(email.value.toString().trim()) -> {
                showErrorMsg("Please enter email-id")

            }
            Validation.isEmailValid(email.value.toString().trim()) -> {
                showErrorMsg("valid Email required")

            }
            Validation.isFieldEmpty(mobileNo.value.toString().trim()) -> {
                showErrorMsg("Please enter mobile number")

            }
            Validation.isMobileValidation(mobileNo.value.toString().trim()) -> {
                showErrorMsg("Valid Mobile required")

            }
            Validation.isMobileValidate(mobileNo.value.toString().trim()) -> {
                showErrorMsg("Please enter minimum 8 and maximum 14 digit mobile number ")

            }
            else -> {
                forgotClick.value = true


            }
        }
    }*/

}


