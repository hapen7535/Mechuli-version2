package com.example.version2mechuli.api

import com.example.version2mechuli.Constants
import com.example.version2mechuli.MenuApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InfoClientMenu {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service : MenuApi by lazy {
        retrofit.create(MenuApi::class.java)
    }

}