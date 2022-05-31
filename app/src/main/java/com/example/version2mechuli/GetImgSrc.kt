package com.example.version2mechuli

data class GetImgSrc (
    val resultList :  List<GetImg>
)

data class GetImg(
    val foodname : String,
    val image_1 : String,
    val image_2: String,
)