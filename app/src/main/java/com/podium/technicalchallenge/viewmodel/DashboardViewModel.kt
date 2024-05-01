package com.podium.technicalchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.podium.technicalchallenge.Repo
import com.podium.technicalchallenge.entity.MovieEntity
import com.podium.technicalchallenge.ui.shared.SortOption
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DashboardViewModel : ViewModel() {
    private val _movies = MutableStateFlow<List<MovieEntity>>(emptyList())
    val movies: StateFlow<List<MovieEntity>> = _movies

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = Repo.getInstance().getMovies()
            Log.d("DemoViewModel", "movies=$movies")
            _movies.value = movies
        }
    }

    fun sortMovies(option: SortOption) {
        viewModelScope.launch(Dispatchers.IO) {
            val sortedMovies = when (option) {
                SortOption.TITLE -> _movies.value.sortedBy { it.title }
                SortOption.TITLE_DESC -> _movies.value.sortedByDescending { it.title }
                SortOption.RELEASE_DATE -> _movies.value.sortedBy { it.releaseDate }
                SortOption.RELEASE_DATE_DESC -> _movies.value.sortedByDescending { it.releaseDate }
                SortOption.RATING -> _movies.value.sortedBy { it.voteAverage }
                SortOption.RATING_DESC -> _movies.value.sortedByDescending { it.voteAverage }
            }
            _movies.value = sortedMovies
        }
    }

    private val _top5Movies = MutableStateFlow<List<MovieEntity>>(emptyList())
    val top5Movies: StateFlow<List<MovieEntity>> = _top5Movies

    fun getTop5Movies() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = Repo.getInstance().getMovies(top5 = true)
            Log.d("DemoViewModel", "movies=$movies")
            _top5Movies.value = movies
        }
    }

    private val _genres = MutableStateFlow<List<String>>(emptyList())
    val genres: StateFlow<List<String>> = _genres

    fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            val genres = Repo.getInstance().getGenres()
            Log.d("DemoViewModel", "genres=$genres")
            _genres.value = genres
        }
    }
}
