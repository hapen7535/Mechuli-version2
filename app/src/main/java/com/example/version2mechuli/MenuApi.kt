package com.example.version2mechuli

import retrofit2.Response
import retrofit2.http.*

interface MenuApi {

    @FormUrlEncoded
    @POST("/registration")
    suspend fun getData(
        @Field("id") id : String,
    ): GetMenuList

}