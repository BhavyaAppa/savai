package com.example.alokozapshopapplication.ui.api

import android.content.ContentValues.TAG
import android.util.Log
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit


/*object ApiRetrofitObject {

    private const val URL_DATA = "https://alokozayshop.ecomextension.com/"

    fun getInstance(): Retrofit {
        val mHttpLoggingInterceptor =
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient=OkHttpClient.Builder().apply {
            addInterceptor(mHttpLoggingInterceptor)
            .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
            .protocols(listOf(Protocol.HTTP_1_1))
            connectTimeout(50000, TimeUnit.SECONDS)
            writeTimeout(1000, TimeUnit.SECONDS)
            readTimeout(1000, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)
        }.build()

        return Retrofit.Builder()
            .baseUrl(URL_DATA)
            .client(mOkHttpClient)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }

    val api: ApiInterface by lazy {
        getInstance().create(ApiInterface::class.java)
    }
}*/

object ApiRetrofitObject {

    private const val URL_DATA = "https://staging.alokozayshop.com/"

    fun getInstance(): Retrofit {
        val mHttpLoggingInterceptor =
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient=OkHttpClient.Builder().apply {
            addInterceptor(mHttpLoggingInterceptor)
                .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
                .protocols(listOf(Protocol.HTTP_1_1))
            connectTimeout(50000, TimeUnit.SECONDS)
            writeTimeout(1000, TimeUnit.SECONDS)
            readTimeout(1000, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)
                .addInterceptor(BasicAuthInterceptor(
                    "stageuser",
                    "Kjf@1plfkE"
                ))
        }.build()

        return Retrofit.Builder()
            .baseUrl(URL_DATA)
            .client(mOkHttpClient)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }

    val api: ApiInterface by lazy {
        getInstance().create(ApiInterface::class.java)
    }
}

internal class BasicAuthInterceptor(username: String, password: String) : Interceptor {
    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()
        Log.d(TAG, "intercept: Authorization Credentials: $credentials")
        return chain.proceed(request)
    }
}




    /*
    private const val URL_DATA="https://alokozayshop.ecomextension.com/mobikulhttp/"
    fun getInstance(): Retrofit {
        val mHttpLoggingInterceptor =
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)


        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

*//*
        OkHttpClient.Builder().apply {
            .addInterceptor(mHttpLoggingInterceptor)
            .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
            .protocols(listOf(Protocol.HTTP_1_1))
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)
        }.build()*//*

        return Retrofit.Builder()
            .baseUrl(URL_DATA)
            .client(mOkHttpClient)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }*/


