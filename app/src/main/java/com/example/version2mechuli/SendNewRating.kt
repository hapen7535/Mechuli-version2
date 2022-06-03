package com.example.version2mechuli

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SendNewRating {

    @FormUrlEncoded
    @POST("/setMenuRating")
    suspend fun requestData(
        @Field("id") id : String,
        @Field("setRatings") ratings : MutableMap<String, Float>,
    ) : SetMenuRating

}