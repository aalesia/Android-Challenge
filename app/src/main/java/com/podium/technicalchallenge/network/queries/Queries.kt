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
            voteAverage
            popularity
          }
        }
        """

    fun getTop5MoviesQuery() =
        """
        query GetTop5MoviesQuery {
          movies(limit: 5, orderBy: "voteAverage", sort: DESC) {
            id
            title
            releaseDate
            posterPath
            voteAverage
            popularity
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
            posterPath
            voteAverage
            popularity
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
            voteAverage
            popularity
            overview
            genres
            cast {
                name
                character
                order
                profilePath
            }
            director {
                id
                name
            }
          }
        }
        """
    }