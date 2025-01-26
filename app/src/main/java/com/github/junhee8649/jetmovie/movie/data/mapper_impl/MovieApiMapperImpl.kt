package com.github.junhee8649.jetmovie.movie.data.mapper_impl

import com.github.junhee8649.jetmovie.common.data.ApiMapper
import com.github.junhee8649.jetmovie.movie.data.remote.models.MovieDto
import com.github.junhee8649.jetmovie.movie.domain.models.Movie
import com.github.junhee8649.jetmovie.utils.GenreConstants


class MovieApiMapperImpl: ApiMapper<List<Movie>, MovieDto> {

    private fun formatEmptyValue(value: String?, default: String = ""): String {
        if (value.isNullOrEmpty()) return "Unknown $default"
        return value
    }

    private fun formatGenre(genreIds: List<Int?>?): List<String> {
        return genreIds?.map { GenreConstants.getGenreNameById(it ?: 0) } ?: emptyList()
    }
}