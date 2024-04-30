package com.podium.technicalchallenge

import com.google.gson.Gson
import com.podium.technicalchallenge.entity.GenreResponse
import com.podium.technicalchallenge.entity.MovieEntity
import com.podium.technicalchallenge.entity.MovieResponse
import com.podium.technicalchallenge.entity.MoviesResponse
import com.podium.technicalchallenge.network.ApiClient
import com.podium.technicalchallenge.network.queries.Queries
import com.podium.technicalchallenge.network.retrofit.GraphQLService
import org.json.JSONObject

class Repo {

    suspend fun getMovies(top5: Boolean = false): List<MovieEntity> {
        val paramObject = JSONObject()
        val query = if (top5) Queries.getTop5MoviesQuery() else Queries.getMoviesQuery()
        paramObject.put("query", query)

        val response = ApiClient.getInstance().provideRetrofitClient().create(GraphQLService::class.java).query(paramObject.toString())
        val jsonBody = response.body()
        val data = Gson().fromJson(jsonBody, MoviesResponse::class.java)
        return data.data.movies
    }

    suspend fun getMoviesForGenre(genre: String): List<MovieEntity> {
        val paramObject = JSONObject()
        val query = Queries.getMoviesForGenreQuery(genre)
        paramObject.put("query", query)

        val response = ApiClient.getInstance().provideRetrofitClient().create(GraphQLService::class.java).query(paramObject.toString())
        val jsonBody = response.body()
        val data = Gson().fromJson(jsonBody, MoviesResponse::class.java)
        return data.data.movies
    }

    suspend fun getGenres(): List<String> {
        val paramObject = JSONObject()
        val query = Queries.getGenresQuery()
        paramObject.put("query", query)

        val response = ApiClient.getInstance().provideRetrofitClient().create(GraphQLService::class.java).query(paramObject.toString())
        val jsonBody = response.body()
        val data = Gson().fromJson(jsonBody, GenreResponse::class.java)
        return data.data.genres
    }

    suspend fun getMovie(id: Int): MovieEntity {
        val paramObject = JSONObject()
        val query = Queries.getMovieQuery(id)
        paramObject.put("query", query)

        val response = ApiClient.getInstance().provideRetrofitClient().create(GraphQLService::class.java).query(paramObject.toString())
        val jsonBody = response.body()
        val data = Gson().fromJson(jsonBody, MovieResponse::class.java)
        return data.data.movie
    }

    companion object {
        private var INSTANCE: Repo? = null
        fun getInstance() = INSTANCE
            ?: Repo().also {
                INSTANCE = it
            }
    }
}
