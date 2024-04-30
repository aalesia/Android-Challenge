package com.podium.technicalchallenge.network.queries

object Queries {
    fun getMoviesQuery() =
        """
        query GetMoviesQuery {
          movies {
            id
            title
            releaseDate
            posterPath
          }
        }
        """

    fun getTop5MoviesQuery() =
        """
        query GetTop5MoviesQuery {
          movies(limit: 5, orderBy: "popularity", sort: DESC) {
            id
            title
            releaseDate
            popularity
            posterPath
          }
        }
        """

    fun getMoviesForGenreQuery(genre: String) =
        """
        query GetMoviesForGenreQuery {
          movies(genre: "$genre") {
            id
            title
            releaseDate
            popularity
            posterPath
          }
        }
        """

    fun getGenresQuery() =
        """
        query GetGenres {
          genres
        }
        """

    fun getMovieQuery(id: Int) =
        """
        query GetMovieQuery {
          movie(id: $id) {
            id
            title
            releaseDate
            popularity
            posterPath
          }
        }
        """
    }