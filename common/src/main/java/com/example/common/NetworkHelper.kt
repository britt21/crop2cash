package com.example.common

sealed class NetworkHelper<T>(val data : T? = null, val message : String? = null) {

    class Success<T>(data: T,message: String? = null) : NetworkHelper<T>(data, message)
    class Error<T>(message: String,data: T? = null) : NetworkHelper<T>(data, message)
    class Loading<T>() : NetworkHelper<T>()
}