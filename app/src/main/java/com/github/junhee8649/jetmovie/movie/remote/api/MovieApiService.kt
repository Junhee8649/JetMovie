package com.github.junhee8649.jetmovie.movie.remote.api

import com.github.junhee8649.jetmovie.movie.remote.models.MovieDto
import com.github.junhee8649.jetmovie.utils.K
import retrofit2.http.GET

interface MovieApiService {
    @GET(K.MOVIE_ENDPOINT)
    suspend fun fetchDiscoverMoive():MovieDto
}