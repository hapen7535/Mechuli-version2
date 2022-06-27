package com.example.version2mechuli.api

import com.example.version2mechuli.Constants.Companion.BASE_URL
import com.example.version2mechuli.SendUserId
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InfoClient {

    private val retrofit by lazy{ //by lazy로 인해 api 변수가 사용될 때 초기화될 수 있다.
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service : SendUserId by lazy {
        retrofit.create(SendUserId::class.java)
    }

}