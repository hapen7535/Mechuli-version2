package com.example.version2mechuli

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.HashMap

//data class GetImgSrc (
//    val resultList :  List<HashMap<String, List<String>>>
//)


data class GetImgSrc (
//    val resultList :  List<GetImg>
    @SerializedName("foodname")
    val foodName : String,

    @SerializedName("image_1")
    val image1 : String,

    @SerializedName("image_2")
    val image2: String,
)