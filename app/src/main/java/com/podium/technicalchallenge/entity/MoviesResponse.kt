package com.podium.technicalchallenge.entity

data class MoviesResponse(
    val data: Movies
)

data class Movies(
    val movies: List<MovieEntity>
)
