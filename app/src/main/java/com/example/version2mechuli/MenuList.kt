package com.example.version2mechuli

import com.google.gson.annotations.SerializedName

data class MenuList(

    val menuList : List<MenuInfo>
)

data class MenuInfo(
    val foodName : String,
    val image_1 : String,
    val image_2 : String,
    val rating : Float,
)