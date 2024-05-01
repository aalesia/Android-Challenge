package com.podium.technicalchallenge.entity

data class MovieResponse(
    val data: Movie
)

data class Movie(
    val movie: MovieEntity
)

data class MovieEntity(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val posterPath: String,
    val voteAverage: Float,
    val popularity: Float,
    val overview: String? = null,
    val genres: List<String>? = null,
    val cast: List<CastEntity>? = null,
    val director: DirectorEntity? = null
)

data class CastEntity(
    val name: String,
    val character: String,
    val order: Int,
    val profilePath: String? = null
)

data class DirectorEntity(
    val id: Int,
    val name: String
)