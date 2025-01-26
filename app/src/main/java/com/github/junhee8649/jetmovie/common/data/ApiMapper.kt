package com.github.junhee8649.jetmovie.common.data

interface ApiMapper<Domain,Entity> {
    fun mapToDomain(apiDto:Entity):Domain

}