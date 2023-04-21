package com.example.alokozapshopapplication.ui.fragment.loginfragment.loginrepositoryrepo



import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.loginfragment.loginmodelclass.LoginRequest
import com.example.alokozapshopapplication.ui.fragment.loginfragment.loginmodelclass.LoginResponse
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface

import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



class LoginRepository(val apiInterface: ApiInterface) : BaseRepository() {




/*    suspend fun userLogin(loginRequest: LoginRequest): Response<LoginResponce> {
        return apiInterface.loginUser(loginRequest)
    }*/


/*    suspend fun userLogin(map : HashMap<String, String>) : Response<LoginResponse>{
        return apiInterface.userLogin(map)
    }*/


/*    private val retrofitObject :ApiInterface by lazy {
        Retrofit.Builder().build().create(ApiInterface::class.java)
    }*/


   // private val apiInterface: ApiInterface = ApiRetrofitObject.getInstance().create(ApiInterface::class.java)

/*    suspend fun userLogin(
        username: String,
        password: String,

    ): Response<LoginResponse> {
        return ApiRetrofitObject.api.userLogin(
            username, password
        )
    }*/

    suspend fun userLogin(loginRequest: LoginRequest): ResponseHandler<LoginResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.userLogin(
                        loginRequest.username.toString(),
                        loginRequest.password.toString()

                        )
                })
        }
    }


}