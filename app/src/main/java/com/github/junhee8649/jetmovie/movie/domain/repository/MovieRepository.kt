package com.github.junhee8649.jetmovie.movie.domain.repository

import com.github.junhee8649.jetmovie.movie.domain.models.Movie
import com.github.junhee8649.jetmovie.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchDiscoverMovie(): Flow<Response<List<Movie>>>
    fun fetchTrendingMovie(): Flow<Response<List<Movie>>>
}