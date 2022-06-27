package com.example.version2mechuli.api

import com.example.version2mechuli.Constants.Companion.BASE_URL
import com.example.version2mechuli.SendUserdata
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object InfoClientData {

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit by lazy{ //by lazy로 인해 api 변수가 사용될 때 초기화될 수 있다.
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service : SendUserdata by lazy {
        retrofit.create(SendUserdata::class.java)
    }

}