package com.github.junhee8649.jetmovie.utils

import android.util.Log
import kotlinx.coroutines.flow.Flow

// Ext 파일은 다음과 같이 when을 반복해서 사용하지 않으려고 만든 확장함수임
//repository.fetchDiscoverMovie().collect { response ->
//    when (response) {
//        is Response.Loading -> { ... }
//        is Response.Success -> { ... }
//        is Response.Error -> { ... }
//    }
//}

//repository.fetchDiscoverMovie().collectAndHandle(
//    onLoading = { ... },
//    onError = { ... },
//) { data -> ... }

suspend fun <T> Flow<Response<T>>.collectAndHandle(
    onError: (Throwable?) -> Unit = {
        Log.e("collectAndHandle", "collectAndHandle: error", it)
    },
    onLoading: () -> Unit = {},
    stateReducer: (T) -> Unit,
) {
    collect { response ->
        when (response) {
            is Response.Error -> {
                onError(response.error)
            }

            is Response.Success -> {
                stateReducer(response.data)
            }

            is Response.Loading -> {
                onLoading()
            }
        }
    }
}