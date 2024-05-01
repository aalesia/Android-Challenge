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
            overview
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
            overview
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
            overview
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