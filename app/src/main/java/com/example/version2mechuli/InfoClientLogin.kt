package com.example.version2mechuli

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InfoClientLogin {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service : SendLoginData by lazy {
        retrofit.create(SendLoginData::class.java)
    }

}