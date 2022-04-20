package com.example.version2mechuli

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface SendUserInfo {

    @FormUrlEncoded
    @POST("/UserData") //데이터 경로 수정 필요
    fun requestData(
        @Field("id") id : String,
        @Field("pw") pw : String,
        @Field("score") score : Int,
    ):Call<GetData> //받아올 데이터 형식

}