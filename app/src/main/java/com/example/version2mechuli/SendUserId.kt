package com.example.version2mechuli

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SendUserId {

    @FormUrlEncoded
    @POST("/idCheck")
    suspend fun requestData( @Field("id") id : String ): GetIdresult // 서버에서 받아올 데이터 형식

}