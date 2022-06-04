package com.example.version2mechuli

import com.google.gson.annotations.SerializedName

data class GetRandomMenuList(
    val menuList : List<RandomMenuData>
)

data class RandomMenuData(
    @SerializedName("foodname")
    val foodName : String,
    @SerializedName("type_1")
    val type_1 : String,
    @SerializedName("type_2")
    val type_2 : String,
    @SerializedName("image_1")
    val image_1 : String,
    @SerializedName("image_2")
    val image_2 : String,
)