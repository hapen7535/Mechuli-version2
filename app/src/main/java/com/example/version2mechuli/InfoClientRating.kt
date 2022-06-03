package com.example.version2mechuli

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InfoClientRating {


    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service : SendNewRating by lazy {
        retrofit.create(SendNewRating::class.java)
    }


}