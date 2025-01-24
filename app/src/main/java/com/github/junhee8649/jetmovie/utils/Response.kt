package com.github.junhee8649.jetmovie.utils

sealed class Response<out T> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val error: Throwable?) : Response<Nothing>()
    data object Loading : Response<Nothing>()
}