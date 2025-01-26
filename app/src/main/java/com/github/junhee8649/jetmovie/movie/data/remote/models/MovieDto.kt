package com.github.junhee8649.jetmovie.movie.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// 데이터 계층: 외부 API, 데이터베이스 등과의 통신을 담당하며, DTO와 매핑 로직을 포함.

@Serializable
data class MovieDto(
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val results: List<Result?>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
)