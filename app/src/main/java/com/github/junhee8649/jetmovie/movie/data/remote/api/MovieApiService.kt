package com.github.junhee8649.jetmovie.movie.data.remote.api

import com.github.junhee8649.jetmovie.movie.data.remote.models.MovieDto
import com.github.junhee8649.jetmovie.utils.K
import retrofit2.http.GET
import retrofit2.http.Query
import com.github.junhee8649.jetmovie.BuildConfig

interface MovieApiService {
    @GET(K.MOVIE_ENDPOINT)
    suspend fun fetchDiscoverMoive(
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false
    ): MovieDto

    @GET(K.TRENDING_MOVIE_ENDPOINT)
    suspend fun fetchTrendingMoive(
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false
    ): MovieDto
}