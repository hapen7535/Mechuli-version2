package com.example.version2mechuli

import com.google.gson.annotations.SerializedName

data class MenuList(

//    val menuNameList : List<MenuName>,
//    val menuImgList : List<MenuImg>,
//    val menuRatingList : List<MenuRating>
//
//)
//
//data class MenuName (
//
//    val menuName : String,
//
//    )
//
//data class MenuImg (
//
//    val menuImg : String,
//
//        )
//
//data class MenuRating (
//
//    val menuRating : Float,
//
//        )


    val menuList : List<MenuInfo>
)

data class MenuInfo(
    val foodName : String,
    val image_1 : String,
    val image_2 : String,
    val rating : Float,
)