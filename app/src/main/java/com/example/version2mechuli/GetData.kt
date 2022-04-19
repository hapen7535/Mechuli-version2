package com.example.version2mechuli

data class GetData(

    var dataList : List<getdata>

)

data class getdata(

    var userId : String,
    var foodName : String,
    var score : Float,

)
