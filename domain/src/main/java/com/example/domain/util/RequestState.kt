package com.example.domain.util

sealed class RequestState<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : RequestState<T>(data)
    class Error<T>(message: String? = null, data: T? = null) : RequestState<T>(data, message)
    class Loading<T> : RequestState<T>()
}
