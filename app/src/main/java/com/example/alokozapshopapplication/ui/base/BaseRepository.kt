package com.example.alokozapshopapplication.ui.base

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.alokozapshopapplication.ui.network.Client.HttpCommonMethod
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.network.model.ResponseData
import com.example.alokozapshopapplication.ui.network.model.ResponseListData
import com.example.alokozapshopapplication.ui.network.model.ErrorWrapper
import com.google.gson.Gson
import com.example.alokozapshopapplication.ui.network.model.HttpErrorCode
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by rajanbhavsar on 20/11/19, 2:02 PM
 * Package Name : com.appname.structure.base
 * Project Name : BrainvireStructure
 */

open class BaseRepository {

    /**
     * This is the Base suspended method which is used for making the call of an Api and
     * Manage the Response with response code to display specific response message or code.
     * @param call ApiInterface method defination to make a call and get response from generic Area.
     */
    suspend fun <T : Any> makeAPICall(call: suspend () -> Response<T>): ResponseHandler<T> {
        try {
            val response = call.invoke()


            when {
                response.code() == 200 -> return ResponseHandler.OnSuccessResponse(response.body())
                response.code() == 401 -> {
                    val body = response.errorBody()
                    var message: String = ""
                    val bodyString = body?.string()
                    val responseWrapper =
                        Gson().fromJson<ErrorWrapper>(bodyString, ErrorWrapper::class.java)
                    message = if (responseWrapper.meta?.status_code == 401) {
                        if (responseWrapper.errors != null) {
                            HttpCommonMethod.getErrorMessage(responseWrapper.errors)
                        } else {
                            responseWrapper.meta?.message.toString()
                        }
                    } else {
                        responseWrapper.meta?.message.toString()
                    }
                    return ResponseHandler.OnFailed(
                        HttpErrorCode.UNAUTHORIZED.code,
                        message,
                        responseWrapper.meta?.message_code.toString()
                    )
                }
                response.code() == 422 -> {
                    val body = response.errorBody()
                    var message: String = ""
                    val bodyString = body?.string()
                    val responseWrapper =
                        Gson().fromJson<ErrorWrapper>(bodyString, ErrorWrapper::class.java)
                    message = if (responseWrapper.meta?.status_code == 422) {
                        if (responseWrapper.errors != null) {
                            HttpCommonMethod.getErrorMessage(responseWrapper.errors)
                        } else {
                            responseWrapper.meta?.message.toString()
                        }
                    } else {
                        responseWrapper.meta?.message.toString()
                    }
                    return ResponseHandler.OnFailed(
                        HttpErrorCode.EMPTY_RESPONSE.code,
                        message,
                        responseWrapper.meta?.message_code.toString()
                    )
                }
                else -> {
                    val body = response.errorBody()
                    var message = ""
                    val bodyString = body?.string()
                    val responseWrapper =
                        Gson().fromJson<ErrorWrapper>(bodyString, ErrorWrapper::class.java)
                    message = if (responseWrapper.meta?.status_code == 422) {
                        if (responseWrapper.errors != null) {
                            HttpCommonMethod.getErrorMessage(responseWrapper.errors)
                        } else {
                            responseWrapper.meta?.message.toString()
                        }
                    } else {
                        responseWrapper.meta?.message.toString()
                    }
                    if (message.isNullOrEmpty()) {
                        return ResponseHandler.OnFailed(
                            HttpErrorCode.EMPTY_RESPONSE.code,
                            message,
                            responseWrapper.meta?.message_code.toString()
                        )
                    } else {
                        return ResponseHandler.OnFailed(
                            HttpErrorCode.NOT_DEFINED.code,
                            message,
                            responseWrapper.meta?.message_code.toString()
                        )
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("Error = ", e.message.toString())
            return if (e is SocketTimeoutException ||
                e is UnknownHostException ||
                e is IOException ||
                e is NetworkErrorException ||
                e is ConnectionShutdownException
            ) {
                ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code, "", "")
            } else {
                ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, "", "")
            }
            /* if (e is CancellationException) {
                 return ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code, "")
             } else {
                 return if (e is SocketTimeoutException ||
                     e is UnknownHostException ||
                     e is IOException ||
                     e is NetworkErrorException ||
                     e is ConnectionShutdownException
                 ) {
                     ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code, "")
                 } else {
                     ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, "")
                 }
             }*/
        }


    }

    suspend fun <T : Any> makeAPICallForList(call: suspend () -> Response<ResponseListData<T>>): ResponseHandler<ResponseListData<T>?> {
        try {
            val response = call.invoke()
            when {
                response.code() == 200 -> return ResponseHandler.OnSuccessResponse(response.body())
                response.code() == 401 -> {
                    val body = response.errorBody()
                    var message: String = ""
                    val bodyString = body?.string()
                    val responseWrapper =
                        Gson().fromJson<ErrorWrapper>(bodyString, ErrorWrapper::class.java)
                    message = if (responseWrapper.meta?.status_code == 401) {
                        if (responseWrapper.errors != null) {
                            HttpCommonMethod.getErrorMessage(responseWrapper.errors)
                        } else {
                            responseWrapper.meta?.message.toString()
                        }
                    } else {
                        responseWrapper.meta?.message.toString()
                    }
                    return ResponseHandler.OnFailed(
                        HttpErrorCode.UNAUTHORIZED.code,
                        message,
                        responseWrapper.meta?.message_code.toString()
                    )
                }
                response.code() == 422 -> {
                    val body = response.errorBody()
                    var message: String = ""
                    val bodyString = body?.string()
                    val responseWrapper =
                        Gson().fromJson<ErrorWrapper>(bodyString, ErrorWrapper::class.java)
                    message = if (responseWrapper.meta?.status_code == 422) {
                        if (responseWrapper.errors != null) {
                            HttpCommonMethod.getErrorMessage(responseWrapper.errors)
                        } else {
                            responseWrapper.meta?.message.toString()
                        }
                    } else {
                        responseWrapper.meta?.message.toString()
                    }
                    return ResponseHandler.OnFailed(
                        HttpErrorCode.EMPTY_RESPONSE.code,
                        message,
                        responseWrapper.meta?.message_code.toString()
                    )
                }
                else -> {
                    val body = response.errorBody()
                    var message = ""
                    val bodyString = body?.string()
                    val responseWrapper =
                        Gson().fromJson<ErrorWrapper>(bodyString, ErrorWrapper::class.java)
                    message = if (responseWrapper.meta?.status_code == 422) {
                        if (responseWrapper.errors != null) {
                            HttpCommonMethod.getErrorMessage(responseWrapper.errors)
                        } else {
                            responseWrapper.meta?.message.toString()
                        }
                    } else {
                        responseWrapper.meta?.message.toString()
                    }
                    if (message.isNullOrEmpty()) {
                        return ResponseHandler.OnFailed(
                            HttpErrorCode.EMPTY_RESPONSE.code,
                            message,
                            responseWrapper.meta?.message_code.toString()
                        )
                    } else {
                        return ResponseHandler.OnFailed(
                            HttpErrorCode.NOT_DEFINED.code,
                            message,
                            responseWrapper.meta?.message_code.toString()
                        )
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("Error = ", e.message.toString())
            return if (e is SocketTimeoutException ||
                e is UnknownHostException ||
                e is IOException ||
                e is NetworkErrorException ||
                e is ConnectionShutdownException
            ) {
                ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code, "", "")
            } else {
                ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, "", "")
            }
            /* if (e is CancellationException) {
                 return ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code, "")
             } else {
                 return if (e is SocketTimeoutException ||
                     e is UnknownHostException ||
                     e is IOException ||
                     e is NetworkErrorException ||
                     e is ConnectionShutdownException
                 ) {
                     ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code, "")
                 } else {
                     ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, "")
                 }
             }*/
        }


    }
}