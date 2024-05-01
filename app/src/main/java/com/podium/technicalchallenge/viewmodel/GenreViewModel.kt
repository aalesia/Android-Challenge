package com.podium.technicalchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.podium.technicalchallenge.Repo
import com.podium.technicalchallenge.entity.MovieEntity
import com.podium.technicalchallenge.enums.SortOption
import com.podium.technicalchallenge.usecase.SortMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GenreViewModel : ViewModel() {
    private val sortMoviesUseCase = SortMoviesUseCase()

    private val _movies = MutableStateFlow<List<MovieEntity>>(emptyList())
    val movies: StateFlow<List<MovieEntity>> = _movies

    fun getMovies(genre: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = Repo.getInstance().getMoviesForGenre(genre)
            Log.d("GenreViewModel", "movies=$movies")
            _movies.value = movies
        }
    }

    fun sortMovies(option: SortOption) {
        viewModelScope.launch(Dispatchers.IO) {
            val sortedMovies = sortMoviesUseCase(option, _movies.value)
            _movies.value = sortedMovies
        }
    }
}