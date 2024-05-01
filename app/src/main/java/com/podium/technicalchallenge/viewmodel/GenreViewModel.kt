package com.podium.technicalchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.podium.technicalchallenge.Repo
import com.podium.technicalchallenge.entity.MovieEntity
import com.podium.technicalchallenge.ui.shared.SortOption
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GenreViewModel : ViewModel() {
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
}