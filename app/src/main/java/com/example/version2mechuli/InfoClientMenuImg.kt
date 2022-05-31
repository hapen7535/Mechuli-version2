package com.example.version2mechuli

import com.example.version2mechuli.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InfoClientMenuImg {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service : SendMenuNameGetMenuImg by lazy {
        retrofit.create(SendMenuNameGetMenuImg::class.java)
    }

}