package com.example.version2mechuli

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName
import java.util.*

data class GetImgSrc (
//    val resultList :  List<GetImg>
    @SerializedName("foodname")
    val foodName : String,

    @SerializedName("image_1")
    val image1 : String,

    @SerializedName("image_2")
    val image2: String,
)