package com.example.version2mechuli

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SendMenuNameGetMenuImg {

    @FormUrlEncoded
    @POST("/getMenuImg")
    suspend fun requestData( @Field("nameList") nameList : ArrayList<String> ): GetImgSrc // 서버에서 받아올 데이터 형식

}