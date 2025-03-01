package com.github.junhee8649.jetmovie.di

import com.github.junhee8649.jetmovie.common.data.ApiMapper
import com.github.junhee8649.jetmovie.movie.data.mapper_impl.MovieApiMapperImpl
import com.github.junhee8649.jetmovie.movie.data.remote.api.MovieApiService
import com.github.junhee8649.jetmovie.movie.data.remote.models.MovieDto
import com.github.junhee8649.jetmovie.movie.data.repository_impl.MovieRepositoryImpl
import com.github.junhee8649.jetmovie.movie.domain.models.Movie
import com.github.junhee8649.jetmovie.movie.domain.repository.MovieRepository
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

// 모듈 생성을 알림
@Module
// 스코프 지정
@InstallIn(SingletonComponent::class)
object MovieModule {

    // JSON 파서 구성
    private val json = Json {
        // 서버에서 오는 입력 값을 강제로 처리하여 API 충돌 방지
        coerceInputValues = true
        //  JSON에는 있지만 데이터 클래스에 없는 키를 무시
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    // MovieApiService와 MovieMapper 두 가지 의존성을 주입하기 위해서
    // MovieRepository를 반환하고 MovieRepositoryImpl로 초기화
    fun provideMovieRepository(
        movieApiService: MovieApiService,
        mapper: ApiMapper<List<Movie>, MovieDto>
    ):MovieRepository = MovieRepositoryImpl(
        movieApiService, mapper
    )

    // Dagger Hilt는 여기서 사용하는 MovieApiService와 Mapper가 무엇인지 모르니
    // 두 의존성을 이해할 수 있도록 여기에 Mapper도 제공
    @Provides
    @Singleton
    fun provideMovieMapper(): ApiMapper<List<Movie>, MovieDto> = MovieApiMapperImpl()

    @Provides
    @Singleton
    fun provideMovieApiService(): MovieApiService{
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(MovieApiService::class.java)
    }
}