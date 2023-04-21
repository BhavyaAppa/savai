package com.example.alokozapshopapplication.ui.fragment.loginfragment.loginviewmodelclass

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.loginfragment.loginmodelclass.LoginRequest
import com.example.alokozapshopapplication.ui.fragment.loginfragment.loginmodelclass.LoginResponse
import com.example.alokozapshopapplication.ui.fragment.loginfragment.loginrepositoryrepo.LoginRepository

import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler

import com.example.alokozapshopapplication.ui.utils.Validation

import kotlinx.coroutines.*


class LoginViewModel: ViewModelBase() {


    var loginRequestData: LoginRequest? = null
    val password: ObservableField<String> = ObservableField("")


    var loginResponseLiveData = MutableLiveData<ResponseHandler<LoginResponse>>()

    var loginRepository: LoginRepository? =
        LoginRepository(ApiClient.getApiInterface())

    init {
        loginRequestData = LoginRequest()
    }



    fun loginValidate() {
        when {
            Validation.isFieldEmpty(loginRequestData?.username.toString().trim()) && (
                    !Validation.isFieldEmpty(loginRequestData?.password.toString().trim()) ||
                            Validation.isFieldEmpty(loginRequestData?.password.toString().trim())) &&
                    Validation.isFieldEmpty(loginRequestData?.mobileNo.toString().trim()) -> {
                showSnackbarMessage("Please enter email-id or number no ")
            }
            Validation.isFieldEmpty(loginRequestData?.mobileNo.toString().trim()) && (
                    !Validation.isFieldEmpty(loginRequestData?.password.toString().trim()) ||
                            Validation.isFieldEmpty(loginRequestData?.password.toString().trim())) &&
                    Validation.isEmailValid(loginRequestData?.username.toString().trim()) -> {
                showSnackbarMessage("valid Email required")
            }
            Validation.isFieldEmpty(loginRequestData?.mobileNo.toString().trim()) &&
                    (!Validation.isEmailValid(loginRequestData?.username.toString().trim()) ||
                            Validation.isEmailValid(loginRequestData?.username.toString().trim())) &&
                    Validation.isFieldEmpty(loginRequestData?.password.toString().trim()) -> {
                showSnackbarMessage("Please enter Password")
            }
            Validation.isFieldEmpty(loginRequestData?.mobileNo.toString().trim()) &&
                    (!Validation.isEmailValid(loginRequestData?.username.toString().trim()) ||
                            Validation.isEmailValid(loginRequestData?.username.toString().trim())) &&
                    !Validation.isValidPassword(loginRequestData?.password.toString().trim()) -> {
                showSnackbarMessage(
                    "Password must contain 3 type of this 4 option" +
                            "(1 upper case,1 lower case,1 special character,1 digit)" +
                            "& minimum 8 characters."
                )
            }
            Validation.isFieldEmpty(loginRequestData?.username.toString().trim()) &&
                    Validation.isFieldEmpty(loginRequestData?.password.toString().trim()) &&
                    Validation.isMobileValidation(loginRequestData?.mobileNo.toString().trim()) -> {
                showSnackbarMessage("Valid Mobile required")
            }
            Validation.isFieldEmpty(loginRequestData?.username.toString().trim()) &&
                    Validation.isFieldEmpty(loginRequestData?.password.toString().trim()) &&
                    Validation.isMobileValidate(loginRequestData?.mobileNo.toString().trim()) -> {
                showSnackbarMessage("Please enter minimum 8 and maximum 14 digit mobile number ")
            }
            else -> {
                userLogin()
            }
        }
    }



    fun userLogin() {

        viewModelScope.launch(coroutineContext) {
            loginResponseLiveData.value = ResponseHandler.Loading
            loginResponseLiveData.value = loginRepository?.userLogin(loginRequestData!!)
        }

    }
}










/*

    val loginError = MutableLiveData<String>()

*/
/*    var job: Job? = null*//*


    var loginRequestData = LoginRequest()

    private var loginResponce: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()
    val loginResponseLiveData: MutableLiveData<BaseResponse<LoginResponse>>
        get() = loginResponce

    private fun showErrorMsg(msg: String) {
        loginError.value = msg
    }


    fun loginValidate() {
        when {
            Validation.isFieldEmpty(loginRequestData.username.toString().trim()) && (
                    !Validation.isFieldEmpty(loginRequestData.password.toString().trim()) ||
                            Validation.isFieldEmpty(loginRequestData.password.toString().trim())) &&
                    Validation.isFieldEmpty(loginRequestData.mobileNo.toString().trim()) -> {
                showErrorMsg("Please enter email-id or number no ")
            }
            Validation.isFieldEmpty(loginRequestData.mobileNo.toString().trim()) && (
                    !Validation.isFieldEmpty(loginRequestData.password.toString().trim()) ||
                            Validation.isFieldEmpty(loginRequestData.password.toString().trim())) &&
                    Validation.isEmailValid(loginRequestData.username.toString().trim()) -> {
                showErrorMsg("valid Email required")
            }
            Validation.isFieldEmpty(loginRequestData.mobileNo.toString().trim()) &&
                    (!Validation.isEmailValid(loginRequestData.username.toString().trim()) ||
                            Validation.isEmailValid(loginRequestData.username.toString().trim())) &&
                    Validation.isFieldEmpty(loginRequestData.password.toString().trim()) -> {
                showErrorMsg("Please enter Password")
            }
            Validation.isFieldEmpty(loginRequestData.mobileNo.toString().trim()) &&
                    (!Validation.isEmailValid(loginRequestData.username.toString().trim()) ||
                            Validation.isEmailValid(loginRequestData.username.toString().trim())) &&
                    !Validation.isValidPassword(loginRequestData.password.toString().trim()) -> {
                showErrorMsg(
                    "Password must contain 3 type of this 4 option" +
                            "(1 upper case,1 lower case,1 special character,1 digit)" +
                            "& minimum 8 characters."
                )
            }
            Validation.isFieldEmpty(loginRequestData.username.toString().trim()) &&
                    Validation.isFieldEmpty(loginRequestData.password.toString().trim()) &&
                    Validation.isMobileValidation(loginRequestData.mobileNo.toString().trim()) -> {
                showErrorMsg("Valid Mobile required")
            }
            Validation.isFieldEmpty(loginRequestData.username.toString().trim()) &&
                    Validation.isFieldEmpty(loginRequestData.password.toString().trim()) &&
                    Validation.isMobileValidate(loginRequestData.mobileNo.toString().trim()) -> {
                showErrorMsg("Please enter minimum 8 and maximum 14 digit mobile number ")
            }
            else -> {



*/
/*                val requestBodyPart:RequestBody=MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("websiteId","1")
                    .addFormDataPart("storeId","1")
                    .addFormDataPart("quoteId","0")
                    .addFormDataPart("width","1440")
                    .addFormDataPart("mFactor","3.5")
                    .addFormDataPart("currency","AED")
                    .addFormDataPart("token","")
                    .addFormDataPart("os","android")
                    .addFormDataPart("username",loginRequestData.username.toString())
                    .addFormDataPart("password",loginRequestData.password.toString())

                    .build()
                loginUser(requestBodyPart)*//*


*/
/*                val hasMap=HashMap<String,String>()
                hasMap.put("websiteId","1")
                hasMap.put("storeId","1")
                hasMap.put("quoteId","0")
                hasMap.put("width","1440")
                hasMap.put("mFactor","3.5")
                hasMap.put("currency","AED")
                hasMap.put("token","")
                hasMap.put("os","android")
                hasMap.put("username","mubarak.ansari@brainvire.com")
                hasMap.put("password","Brain@2022")

                loginUser(hasMap)*//*


                loginUser(
                    loginRequestData.username.toString(),
                    loginRequestData.password.toString(),

                )


            }
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }
*/
/*    var job:String?=null*//*



    private fun loginUser(
        username: String,
        password: String,

    ) {

        viewModelScope.launch(Dispatchers.IO+coroutineExceptionHandler){
            loginResponce.postValue( BaseResponse.Loading())
            */
/*  Log.d("ResponseLogin", loginRequest.toString())*//*

            try {
                val response = loginRepository.userLogin(
                    username,
                    password,
                )
                if (response.isSuccessful) {
                    Log.d("ResponseLoginSuccess", response.body().toString())
                    loginResponce.postValue( BaseResponse.Success(response.body()))
                    //loginResponce.value = BaseResponse.Success(response.body())
                } else {
                    Log.d("ResponseLoginError", response.body().toString())
                    //loginResponce.value = BaseResponse.Error(response.message())
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                Log.d("ResponseLoginException", ex.message.toString())
                //loginResponce.value = BaseResponse.Error(ex.message)
            } catch (ex: CancellationException) {
                throw  ex
            }
        }

    }
}


*/








/*    private fun loginUser(hashMap: HashMap<String,String>) {
         viewModelScope.launch {
                Log.d("ResponseLogin", hashMap.toString())

                try {
                    val response = loginRepository.userLogin(hashMap)
                    if (response.isSuccessful) {
                        Log.d("ResponseLoginSuccess", response.body().toString())
                        loginResponce.postValue(response.body())
                        //loginResponce.value = BaseResponse.Success(response.body())
                    } else {
                        Log.d("ResponseLoginError", response.body().toString())
                        //loginResponce.value = BaseResponse.Error(response.message())
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    Log.d("ResponseLoginException", ex.message.toString())
                    //loginResponce.value = BaseResponse.Error(ex.message)
                } catch (ex: CancellationException) {
                    throw  ex

                }

        }

    }*/

/*    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }*/


/*    private fun loginUser(loginRequest: LoginRequest) {

        viewModelScope.launch {
            try {

                val response = loginRepository.setLogin(loginRequest).await()
                Log.d("Test_log 2", response.body().toString())
                if (response.isSuccessful) {
                    Log.d("TEST-LOG1", response.body().toString())
                } else {
                    Log.d("TEST-LOG2", response.body().toString())
                }
            } catch (e: Exception) {

                Log.d("TEST-LOG3", e.message.toString())
            }
        }
    }
    }*/

/*
private fun loginUser(
    username: String,
    password: String,
    storeId: String,
    websiteId: String,
    currency: String,
    mFactor: String,
    width: String,
    quoteId: String,
    token: String,
    os: String
) {

    viewModelScope.launch {
        //loginResponce.value = BaseResponse.Loading()
        *//*  Log.d("ResponseLogin", loginRequest.toString())*//*
        try {
            val response = loginRepository.setLogin(
                username,
                password,
                storeId,
                websiteId,
                currency,
                mFactor,
                width,
                quoteId,
                token,
                os
            )

            if (response.isSuccessful) {
                Log.d("ResponseLoginSuccess", response.body().toString())
                //loginResponce.value = BaseResponse.Success(response.body())
            } else {
                Log.d("ResponseLoginError", response.body().toString())
                //loginResponce.value = BaseResponse.Error(response.message())
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.d("ResponseLoginException", ex.message.toString())
            //loginResponce.value = BaseResponse.Error(ex.message)
        } catch (ex: CancellationException) {
            throw  ex
        }
    }
}*/


/*
    var email = MutableLiveData<String>("")
    var mobileNo = MutableLiveData<String>("")
    var password = MutableLiveData<String>("")
*/

/*    var email = MutableLiveData<String>()
    var mobileNo = MutableLiveData<String>()
    var password = MutableLiveData<String>()*/

/*    fun loginValidate(){
        when{
            Validation.isEmailEmpty(email.value.toString().trim())->{
                showErrorMsg("Email not empty")
            }
            Validation.isEmailValid(email.value.toString().trim())->{
                showErrorMsg("valid Email required")
            }
            Validation.isPasswordEmpty(password.value.toString().trim())->{
                showErrorMsg("Password not empty")
            }
            Validation.isPasswordMinimumLength(password.value.toString().trim())->{
                showErrorMsg("Password should be contain att list 6 character")
            }
            Validation.isPasswordDigit(password.value.toString().trim())->{
                showErrorMsg("Password should be contain att list 1 digit ")
            }
            Validation.isPasswordUpperCase(password.value.toString().trim())->{
                showErrorMsg("Password should be contain att list 1 upper case")
            }
            Validation.isPasswordLowerCase(password.value.toString().trim())->{
                showErrorMsg("Password should be contain att list 1 lower case")
            }
            Validation.isPasswordSpecialChar(password.value.toString().trim())->{
                showErrorMsg("Password should be contain att list 1 special char")
            }
            Validation.isMobileEmpty(mobileNo.value.toString().trim())->{
                showErrorMsg("Mobile not empty")
            }
            Validation.isMobileValidation(mobileNo.value.toString().trim())->{
                showErrorMsg("Valid Mobile required")
            }
            Validation.isMobileValidate(mobileNo.value.toString().trim())->{
                showErrorMsg("Mobile should be contains only 10 digit ")
            }else->{
                loginClick.value=true
            }
        }

    }*/

/*    fun loginValidate() {
        when {
            ValidationFunction.isFieldEmpty(email.value.toString().trim()) -> {
                showErrorMsg("Email not empty")
            }
            !ValidationFunction.isEmailValidate(email.value.toString().trim()) -> {
                showErrorMsg("valid Email required")
            }
            ValidationFunction.isFieldEmpty(password.value.toString().trim()) -> {
                showErrorMsg("Password not empty")
            }
            !ValidationFunction.isPasswordValidate(password.value.toString()) -> {
                showErrorMsg("Password should be contain At list 6 character")
            }
            !ValidationFunction.isPasswordValidate(password.value.toString()) -> {
                showErrorMsg("Password should be contain At list 1 digit ")
            }
            !ValidationFunction.isPasswordValidate(password.value.toString()) -> {
                showErrorMsg("Password should be contain At list 1 upper case")
            }
            !ValidationFunction.isPasswordValidate(password.value.toString()) -> {
                showErrorMsg("Password should be contain At list 1 lower case")
            }
            !ValidationFunction.isPasswordValidate(password.value.toString()) -> {
                showErrorMsg("Password should be contain At list 1 special char")
            }

            Validation.isMobileEmpty(mobileNo.value.toString().trim()) -> {
                showErrorMsg("Password not empty")
            }
            Validation.isMobileValidation(mobileNo.value.toString().trim()) -> {
                showErrorMsg("Valid Mobile required")
            }
            Validation.isMobileValidate(mobileNo.value.toString().trim()) -> {
                showErrorMsg("Mobile should be contains only 10 digit ")
            }
            else -> {
                loginClick.value = true
            }
        }

    }*/

/*

    fun loginValidate() {

        when {
            Validation.isFieldEmpty(loginRequestData.username.toString().trim()) -> {
                showErrorMsg("Please enter email-id")
            }
            Validation.isEmailValid(loginRequestData.username.toString().trim()) -> {
                showErrorMsg("valid Email required")
            }
            Validation.isFieldEmpty(loginRequestData.password.toString().trim()) -> {
                showErrorMsg("Please enter Password  ")
            }
            !Validation.isValidPassword(loginRequestData.password.toString().trim()) -> {
                showErrorMsg(
                    "Password must contain 3 type of this 4 option" +
                            "(1 upper case,1 lower case,1 special character,1 digit)" +
                            "& minimum 8 characters."
                )
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
                loginClick.value = true
                loginUser(LoginRequest(loginRequestData.username.toString(),loginRequestData.password.toString()))
            }
        }

    }
*/

/*init {
    viewModelScope.launch {
        //loginResponce.value = BaseResponse.Loading()
        Log.d("ResponseLogin", loginRequest.toString())
        val response = loginRepository.loginUser(LoginRequest())
        try {


            when {
                response.isSuccessful -> {
                    Log.d("ResponseLoginSuccess", response.body().toString())
                  //  loginResponce.value = BaseResponse.Success(response.body())
                }
                else -> {
                    Log.d("ResponseLoginError", response.body().toString())
                    //loginResponce.value = BaseResponse.Error(response.message())
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.d("ResponseLoginException", ex.message.toString())
            //loginResponce.value = BaseResponse.Error(ex.message)
        } catch (ex: CancellationException) {
            throw  ex
        }
    }

}*/