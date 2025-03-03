package com.github.junhee8649.jetmovie.movie.data.repository_impl

import com.github.junhee8649.jetmovie.common.data.ApiMapper
import com.github.junhee8649.jetmovie.movie.data.remote.api.MovieApiService
import com.github.junhee8649.jetmovie.movie.data.remote.models.MovieDto
import com.github.junhee8649.jetmovie.movie.domain.models.Movie
import com.github.junhee8649.jetmovie.movie.domain.repository.MovieRepository
import com.github.junhee8649.jetmovie.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val apiMapper: ApiMapper<List<Movie>, MovieDto>
):MovieRepository {
    override fun fetchDiscoverMovie(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = movieApiService.fetchDiscoverMoive()
        apiMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        emit(Response.Error(e))
    }

    override fun fetchTrendingMovie(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = movieApiService.fetchTrendingMoive()
        apiMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        emit(Response.Error(e))
    }
}