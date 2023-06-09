package com.example.alokozapshopapplication.ui.network.Client

import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by rajanbhavsar on 20/11/19, 3:58 PM
 * Package Name : com.appname.structure.network.Client
 * Project Name : BrainvireStructure
 */
class HttpHandleIntercept : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().headers(getJsonHeader()).build()
        val response: Response?

        response = chain.proceed(request)
        if (response.code == 401) {
            return ApiClient.generateCustomResponse(
                401, "",
                chain.request()
            )!!

        } else if (response.code == 500) {
            return ApiClient.generateCustomResponse(
                500, "",
                chain.request()
            )!!


        }

        return response
    }

    private fun getJsonHeader(): Headers {
        val builder = Headers.Builder()
        builder.add("Content-Type", "application/json")
        builder.add("Accept", "application/json")
        builder.add("Accept-Language", HttpCommonMethod.getLanguageCode())
        builder.add("is-mobile", "1")
        builder.add("lang-code", "en")

        if (MyPreference.getValueBoolean(PrefKey.ISLOGIN, false)) {
            //builder.add("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjE5ZjM0OTdmYWY3NWY2ZmM0YmFhOWU5ZjU4ZjAwMGExYjRkZTM4YmRkMGI1OTU2ZTJiMWRmZjY5ZWQxMDJmOGNlNGE4NjQyMmRkNWI4MWI3In0.eyJhdWQiOiI0IiwianRpIjoiMTlmMzQ5N2ZhZjc1ZjZmYzRiYWE5ZTlmNThmMDAwYTFiNGRlMzhiZGQwYjU5NTZlMmIxZGZmNjllZDEwMmY4Y2U0YTg2NDIyZGQ1YjgxYjciLCJpYXQiOjE1Nzc0MjU4MTYsIm5iZiI6MTU3NzQyNTgxNiwiZXhwIjoxNjA5MDQ4MjEwLCJzdWIiOiIzNSIsInNjb3BlcyI6W119.LLWVVu0L2xSJurqyK1GV4EkEMTJnCAzo6lVpf0hxk1hQy5PmyzeTIr5i5md9CyVCvuiRt7MnPY9OdMjKtLy2H7-Z0dort501fvmWoUMKNjxTQxldsOzzXd_SKcIF0W9251NnOv7Fp7LUzfUVKmarafy_GFwoOF9YwwoLLlIsLIx38EVIYLVh6q-pnQmRNm8RWco23ZX0g1IBCIaWfdoDK6A0iED_ZtjzlbL1dIh44xvXFgAkJRR0F-SYntz9xsATL7cyelFgAuFt41oKmg-pOzdfG1BTbxGAElSriCTdl8_TZ0wUxYH-QRDBx26Xz6hn69xAd1GIlQTw5zYh-2oDT8p0zOVJFkhxRvgDrPirBpYUcElS3L99BtYUTNNGbD4lmmbjVvOs67nh0xPsRyWYG5cdMDcbKpAz2aARW_ENkwnK1BOlxieCB6hFizp7vaajBY8JcA8LfbUpFj3-Vr0kZUGUeNK9-zs5Vi4fz7goAS7u_XY0taapUk7lHDqY4vUIFn04EQYbwVk8us_M-CtcT9--ZSgstYk-BnhAK4d0C0Qy8E17z_3kdIw01uaO6XytXGkEwSHuYZHfYvyubpq4-2YB5UOHXLH9cb6gu0d_sjIsWNtV4j1WVRO7a-15Z3VGFqOBpK-YcY4kZ9lgnpew-oGDD5k6oaipDi2M0Y1cv-A")
            builder.add("Authorization", HttpCommonMethod.getToken())
        }

        return builder.build()
    }
}