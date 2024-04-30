package com.podium.technicalchallenge.entity

data class MoviesResponse(
    val data: Movies
)

data class Movies(
    val movies: List<MovieEntity>
)

data class MovieEntity(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val posterPath: String
)
