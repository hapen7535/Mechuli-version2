package com.example.version2mechuli

import com.google.gson.annotations.SerializedName

data class GetImgSrcList (
    val resultList :  List<GetImgSrc>
)

data class GetImgSrc (
    @SerializedName("foodname")
    val foodName : String,

    @SerializedName("image_1")
    val image1 : String,

    @SerializedName("image_2")
    val image2: String,
)