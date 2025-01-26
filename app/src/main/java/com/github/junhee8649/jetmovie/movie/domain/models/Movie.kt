package com.github.junhee8649.jetmovie.movie.domain.models

// 데이터 계층과 도메인 계층을 분리 (여기는 도메인 계층)
// 도메인 계층: 비즈니스 로직과 도메인 모델을 포함.

data class Movie(
    val backdropPath: String,
    val genreIds: List<String>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val video: Boolean
)
