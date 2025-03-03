package com.github.junhee8649.jetmovie.di

import com.github.junhee8649.jetmovie.common.data.ApiMapper
import com.github.junhee8649.jetmovie.movie.data.remote.models.MovieDto
import com.github.junhee8649.jetmovie.movie.domain.models.Movie
import com.github.junhee8649.jetmovie.movie_detail.data.mapper_impl.MovieDetailMapperImpl
import com.github.junhee8649.jetmovie.movie_detail.data.remote.api.MovieDetailApiService
import com.github.junhee8649.jetmovie.movie_detail.data.remote.models.MovieDetailDto
import com.github.junhee8649.jetmovie.movie_detail.data.repo_impl.MovieDetailRepositoryImpl
import com.github.junhee8649.jetmovie.movie_detail.domain.models.MovieDetail
import com.github.junhee8649.jetmovie.movie_detail.domain.repository.MovieDetailRepository
import com.github.junhee8649.jetmovie.utils.K
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MovieDetailModule {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }


    @Provides
    @Singleton
    // MovieApiService와 MovieMapper, MovieDetailMapper 세 가지 의존성을 주입하기 위해서
    // MovieRepository를 반환하고 MovieRepositoryImpl로 초기화
    fun provideMovieDetailRepository(
        movieDetailApiService: MovieDetailApiService,
        mapper: ApiMapper<MovieDetail, MovieDetailDto>,
        movieMapper: ApiMapper<List<Movie>, MovieDto>
    ): MovieDetailRepository = MovieDetailRepositoryImpl(
        movieDetailApiService = movieDetailApiService,
        apiDetailMapper = mapper,
        apiMovieMapper = movieMapper,
    )

    // provideMovieMapper()를 통해 ApiMapper<MovieDetail, MovieDetailDto> 타입의 의존성을 제공
    @Provides
    @Singleton
    fun provideMovieMapper(): ApiMapper<MovieDetail, MovieDetailDto> = MovieDetailMapperImpl()

    // provideMovieDetailApiService()를 통해 MovieDetailApiService 타입의 의존성을 제공
    @Provides
    @Singleton
    fun provideMovieDetailApiService(): MovieDetailApiService {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(MovieDetailApiService::class.java)
    }




}