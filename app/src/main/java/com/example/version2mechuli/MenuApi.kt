package com.example.version2mechuli

import android.text.Editable
import retrofit2.Response
import retrofit2.http.*

interface MenuApi {

    @FormUrlEncoded
    @POST("/getMenuList")
    suspend fun getData(
        @Field("id") id : String,
//        @Field("menu_name") menuName : String,
    ): GetMenuList

}