package com.example.version2mechuli

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SendLoginData {

        @FormUrlEncoded
        @POST("/login")
        suspend fun requestData(
            @Field("id") id : String,
            @Field("pw") pw : String,
        ): GetLoginRes // 서버에서 받아올 데이터 형식

}