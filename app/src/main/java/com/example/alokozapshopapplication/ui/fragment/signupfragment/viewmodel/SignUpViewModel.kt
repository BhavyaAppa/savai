package com.example.alokozapshopapplication.ui.fragment.signupfragment.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.signupfragment.repository.SignUpRepository
import com.example.alokozapshopapplication.ui.fragment.signupfragment.signupmodel.SignUpRequest
import com.example.alokozapshopapplication.ui.fragment.signupfragment.signupmodel.SignUpResponce
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.BaseResponse
import com.example.alokozapshopapplication.ui.utils.Validation
import com.example.alokozapshopapplication.ui.utils.ValidationField
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel: ViewModelBase() {

    var signUpRequestData: SignUpRequest? = null
    val password: ObservableField<String> = ObservableField("")
    val confPassword: ObservableField<String> = ObservableField("")

    var responseLiveData = MutableLiveData<ResponseHandler<SignUpResponce>>()

    var signUpRepository: SignUpRepository? =
        SignUpRepository(ApiClient.getApiInterface())

    init {
        signUpRequestData = SignUpRequest()
    }




    fun signUpValidate() {

        when {
            ValidationField.isFieldEmpty(signUpRequestData?.firstName.toString().trim()) -> {
                showSnackbarMessage("Please enter first name")
            }
            ValidationField.isNameValidate(signUpRequestData?.firstName.toString().trim()) -> {
                showSnackbarMessage("first name should be minimum 2 and maximum 100 characters")
            }
            ValidationField.isFieldEmpty(signUpRequestData?.lastName.toString().trim()) -> {
                showSnackbarMessage("Please enter last name")
            }
            ValidationField.isNameValidate(signUpRequestData?.lastName.toString().trim()) -> {
                showSnackbarMessage("last name should be minimum 2 and maximum 100 characters")
            }
            ValidationField.isFieldEmpty(signUpRequestData?.mobileNumber.toString().trim()) -> {
                showSnackbarMessage("Please enter mobile number")
            }
            ValidationField.isMobileValidation(signUpRequestData?.mobileNumber.toString().trim()) -> {
                showSnackbarMessage("Valid Mobile required")
            }
            ValidationField.isMobileValidate(signUpRequestData?.mobileNumber.toString().trim()) -> {
                showSnackbarMessage("Please enter minimum 8 and maximum 14 digit mobile number ")
            }
            ValidationField.isFieldEmpty(signUpRequestData?.email.toString().trim()) -> {
                showSnackbarMessage("Please enter email-id")
            }
            ValidationField.isEmailValid(signUpRequestData?.email.toString().trim()) -> {
                showSnackbarMessage("valid Email required")
            }
            ValidationField.isFieldEmpty(signUpRequestData?.password.toString().trim()) -> {
                showSnackbarMessage("Please enter Password  ")
            }
            !ValidationField.isValidPassword(signUpRequestData?.password.toString().trim()) -> {
                showSnackbarMessage(
                    "Password must contain 3 type of this 4 option" +
                            "(1 upper case,1 lower case,1 special character,1 digit)" +
                            "& minimum 8 characters."
                )
            }
            ValidationField.isFieldEmpty(signUpRequestData?.confirmPassword.toString().trim()) -> {
                showSnackbarMessage("Please enter ConfirmPassword")
            }
            signUpRequestData?.confirmPassword != signUpRequestData?.password -> {
                showSnackbarMessage("new password and  confirm new password should be match")
            }
            else -> {
                userSignUp()

            }
        }

    }


    fun userSignUp() {

        viewModelScope.launch(coroutineContext) {
            responseLiveData.value = ResponseHandler.Loading
            responseLiveData.value = signUpRepository?.userSignUp(signUpRequestData!!)
        }

    }


    /*   val signUpError = MutableLiveData<String>()
       val signUpClick = MutableLiveData<Boolean>()


       var signUpRequestData = SignUpRequest()

       private var signUpResponce: MutableLiveData<BaseResponse<SignUpResponce>> = MutableLiveData()
       val signUpResponseLiveData: MutableLiveData<BaseResponse<SignUpResponce>>
           get() = signUpResponce


   *//*    var firstName = MutableLiveData<String>("sk")
    var lastName = MutableLiveData<String>("sk")
    var mobileNo = MutableLiveData<String>("123456789")
    var email = MutableLiveData<String>("savai123@gmail.com" )
    var password = MutableLiveData<String>("Savai@123")
    var confirmPassword = MutableLiveData<String>("Savai@123")
    var referralCode = MutableLiveData<String>()
    var switchOnOff : Boolean = false*//*


    fun signUpValidate() {

        when {
            Validation.isFieldEmpty(signUpRequestData.firstName.toString().trim()) -> {
                showErrorMsg("Please enter first name")
            }
            Validation.isNameValidate(signUpRequestData.firstName.toString().trim()) -> {
                showErrorMsg("first name should be minimum 2 and maximum 100 characters")
            }
            Validation.isFieldEmpty(signUpRequestData.lastName.toString().trim()) -> {
                showErrorMsg("Please enter last name")
            }
            Validation.isNameValidate(signUpRequestData.lastName.toString().trim()) -> {
                showErrorMsg("last name should be minimum 2 and maximum 100 characters")
            }
            Validation.isFieldEmpty(signUpRequestData.mobileNumber.toString().trim()) -> {
                showErrorMsg("Please enter mobile number")
            }
            Validation.isMobileValidation(signUpRequestData.mobileNumber.toString().trim()) -> {
                showErrorMsg("Valid Mobile required")
            }
            Validation.isMobileValidate(signUpRequestData.mobileNumber.toString().trim()) -> {
                showErrorMsg("Please enter minimum 8 and maximum 14 digit mobile number ")
            }
            Validation.isFieldEmpty(signUpRequestData.email.toString().trim()) -> {
                showErrorMsg("Please enter email-id")
            }
            Validation.isEmailValid(signUpRequestData.email.toString().trim()) -> {
                showErrorMsg("valid Email required")
            }
            Validation.isFieldEmpty(signUpRequestData.password.toString().trim()) -> {
                showErrorMsg("Please enter Password  ")
            }
            !Validation.isValidPassword(signUpRequestData.password.toString().trim()) -> {
                showErrorMsg(
                    "Password must contain 3 type of this 4 option" +
                            "(1 upper case,1 lower case,1 special character,1 digit)" +
                            "& minimum 8 characters."
                )
            }
            Validation.isFieldEmpty(signUpRequestData.confirmPassword.toString().trim()) -> {
                showErrorMsg("Please enter ConfirmPassword")
            }
            signUpRequestData.confirmPassword != signUpRequestData.password -> {
                showErrorMsg("new password and  confirm new password should be match")
            }
            else -> {


                userSignUp(

                    signUpRequestData.firstName.toString(),
                    signUpRequestData.lastName.toString(),
                    signUpRequestData.mobileNumber.toString(),
                    signUpRequestData.email.toString(),
                    signUpRequestData.password.toString(),
                    signUpRequestData.confirmPassword.toString()


                )

            }
        }

    }

    private fun showErrorMsg(msg: String) {
        signUpError.value = msg
    }


    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun userSignUp(
        firstName: String,
        lastName: String,
        mobileNumber: String,
        email: String,
        password: String,
        confirmPassword: String


    ) {

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            signUpResponce.postValue(BaseResponse.Loading())
            *//*  Log.d("ResponseLogin", loginRequest.toString())*//*

            val response = signUpRepository.userSignUp(
                firstName,
                lastName,
                mobileNumber,
                email,
                password,
                confirmPassword

            )

            try {
                if (response.isSuccessful) {
                    Log.d("ResponseSignUpSuccess", response.body().toString())
                    signUpResponce.postValue(BaseResponse.Success(response.body()))
                    //loginResponce.value = BaseResponse.Success(response.body())
                } else {
                    Log.d("ResponseSignUpError", response.body().toString())
                    //loginResponce.value = BaseResponse.Error(response.message())
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                Log.d("ResponseSignUpException", ex.message.toString())
                //loginResponce.value = BaseResponse.Error(ex.message)
            } catch (ex: CancellationException) {
                throw  ex
            }
        }

    }*/


}