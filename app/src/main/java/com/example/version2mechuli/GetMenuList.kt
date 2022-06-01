package com.example.version2mechuli

import com.google.gson.annotations.SerializedName

data class GetMenuList(
    val menuList : List<MenuData>
)

data class MenuData(
    @SerializedName("foodname")
    val foodName : String,
    @SerializedName("image_1")
    val image_1 : String,
    @SerializedName("image_2")
    val image_2 : String,
    @SerializedName("rating")
    val rating : Float,
)