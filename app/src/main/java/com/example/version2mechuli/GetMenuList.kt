package com.example.version2mechuli

data class GetMenuList(

    val menuList : List<MenuData>

)

data class MenuData(

    val foodName : String,
    val foodRating : Float

)
