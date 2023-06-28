package com.example.audiobook.core.data.util

data class StandardResponse<T>(
    val succesfull : Boolean ,
    val result : Result<T>
)

data class Result<T>(
    val data : T? = null ,
    val message : String
)