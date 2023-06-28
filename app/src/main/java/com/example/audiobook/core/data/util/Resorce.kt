package com.example.audiobook.core.data.util

sealed class Resource<T>(data : T? = null , error : String){
    data class Success<T>(val data: T?) : Resource<T>(data , "")
    data class Error<T>(val message : String) : Resource<T>(error = message)
}
