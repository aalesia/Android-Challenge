package com.podium.technicalchallenge.usecase

import com.podium.technicalchallenge.entity.MovieEntity
import com.podium.technicalchallenge.enums.SortOption

class SortMoviesUseCase {
    operator fun invoke(option: SortOption, movies: List<MovieEntity>): List<MovieEntity> {
        return when (option) {
            SortOption.TITLE -> movies.sortedBy { it.title }
            SortOption.TITLE_DESC -> movies.sortedByDescending { it.title }
            SortOption.RELEASE_DATE -> movies.sortedBy { it.releaseDate }
            SortOption.RELEASE_DATE_DESC -> movies.sortedByDescending { it.releaseDate }
            SortOption.RATING -> movies.sortedBy { it.voteAverage }
            SortOption.RATING_DESC -> movies.sortedByDescending { it.voteAverage }
        }
    }
}