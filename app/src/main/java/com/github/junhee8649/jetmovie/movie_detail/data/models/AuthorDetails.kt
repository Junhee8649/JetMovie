package com.github.junhee8649.jetmovie.movie_detail.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDetails(
    @SerialName("avatar_path")
    val avatarPath: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("rating")
    val rating: Double? = null,
    @SerialName("username")
    val username: String? = null
)