package com.example.challengevk.utils

sealed class NetworkResult<T>(val message: String? = null) {
    class Success<T>(val data: T) : NetworkResult<T>()
    class Error<T>(message: String? = null) : NetworkResult<T>(message = message)
    class Loading<T>(message: String? = null) : NetworkResult<T>(message = message)
}