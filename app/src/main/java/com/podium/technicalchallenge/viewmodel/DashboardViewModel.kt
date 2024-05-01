package com.podium.technicalchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.podium.technicalchallenge.Repo
import com.podium.technicalchallenge.entity.MovieEntity
import com.podium.technicalchallenge.enums.SortOption
import com.podium.technicalchallenge.usecase.SortMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DashboardViewModel : ViewModel() {
    private val sortMoviesUseCase = SortMoviesUseCase()

    private val _movies = MutableStateFlow<List<MovieEntity>>(emptyList())
    val movies: StateFlow<List<MovieEntity>> = _movies

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = Repo.getInstance().getMovies()
            Log.d("DashboardViewModel", "movies=$movies")
            _movies.value = movies
        }
    }

    fun sortMovies(option: SortOption) {
        viewModelScope.launch(Dispatchers.IO) {
            val sortedMovies = sortMoviesUseCase(option, _movies.value)
            _movies.value = sortedMovies
        }
    }

    private val _top5Movies = MutableStateFlow<List<MovieEntity>>(emptyList())
    val top5Movies: StateFlow<List<MovieEntity>> = _top5Movies

    fun getTop5Movies() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = Repo.getInstance().getMovies(top5 = true)
            Log.d("DashboardViewModel", "movies=$movies")
            _top5Movies.value = movies
        }
    }

    private val _genres = MutableStateFlow<List<String>>(emptyList())
    val genres: StateFlow<List<String>> = _genres

    fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            val genres = Repo.getInstance().getGenres()
            Log.d("DashboardViewModel", "genres=$genres")
            _genres.value = genres
        }
    }
}
