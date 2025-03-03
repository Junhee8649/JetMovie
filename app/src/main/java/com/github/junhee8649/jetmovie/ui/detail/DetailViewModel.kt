package com.github.junhee8649.jetmovie.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.junhee8649.jetmovie.movie.domain.models.Movie
import com.github.junhee8649.jetmovie.movie_detail.domain.models.MovieDetail
import com.github.junhee8649.jetmovie.movie_detail.domain.repository.MovieDetailRepository
import com.github.junhee8649.jetmovie.utils.K
import com.github.junhee8649.jetmovie.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieDetailRepository,
    // Hilt와 Compose Navigation을 함께 사용할 때 매우 유용
    // 화면 회전이나 프로세스 종료와 같은 상황에서도 데이터를 유지할 수 있게 해줌
    // Compose Navigation에서 전달된 인자(예: 영화 ID)를 ViewModel 내에서 쉽게 접근할 수 있게 해줌
    // Hilt를 사용할 때 SavedStateHandle을 ViewModel 생성자에 자동으로 주입해줌
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _detailState = MutableStateFlow(DetailState())
    val detailState = _detailState.asStateFlow()

    val id: Int = savedStateHandle.get<Int>(K.MOVIE_ID) ?: -1

    init {
        fetchMovieDetailById()
    }

    // 데이터 계층에서 구현해놨으니 오직 도메인 계층에서 선언한 인터페이스만 사용하고 생각하면 됌

    private fun fetchMovieDetailById() = viewModelScope.launch {
        if (id == -1) {
            _detailState.update {
                it.copy(isLoading = false, error = "Movie not found")
            }
        } else {
            repository.fetchMovieDetail(id).collectAndHandle(
                onError = { error ->
                    _detailState.update {
                        it.copy(isLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _detailState.update {
                        it.copy(isLoading = true, error = null)
                    }
                }
            ) { movieDetail ->
                _detailState.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        movieDetail = movieDetail
                    )
                }
            }
        }
    }

    fun fetchMovie() = viewModelScope.launch {
        repository.fetchMovie().collectAndHandle(
            onError = { error ->
                _detailState.update {
                    it.copy(isMovieLoading = false, error = error?.message)
                }
            },
            onLoading = {
                _detailState.update {
                    it.copy(isMovieLoading = true, error = null)
                }
            }
        ) { movies ->
            _detailState.update {
                it.copy(
                    isMovieLoading = false,
                    error = null,
                    movies = movies
                )
            }
        }
    }

}

data class DetailState(
    val movieDetail: MovieDetail? = null,
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isMovieLoading: Boolean = false
)