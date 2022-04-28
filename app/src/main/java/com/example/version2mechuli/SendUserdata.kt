package com.example.version2mechuli

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface SendUserdata {

    @FormUrlEncoded
    @POST("/registration")
    fun requestData(
        @Field("id") id : String,
        @Field("pw") pw : String,
    ):Call<GetDataList>

}