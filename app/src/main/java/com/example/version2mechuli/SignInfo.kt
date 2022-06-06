package com.example.version2mechuli

import java.io.Serializable

data class SignInfo(
    var age : String,
    var id : String,
    var pw : String,
    var gender : String,
): Serializable
