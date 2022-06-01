package com.example.version2mechuli

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SendMenuNameGetMenuImg {

    @FormUrlEncoded
    @POST("/getMenuImg")
    suspend fun requestData( @Field("nameList") nameList : ArrayList<String> ): GetImgSrcList

}