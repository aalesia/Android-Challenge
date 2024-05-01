package com.podium.technicalchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.podium.technicalchallenge.Repo
import com.podium.technicalchallenge.entity.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val _movie = MutableStateFlow(MovieEntity(0, "", "", "", 0f, 0f))
    val movie: StateFlow<MovieEntity> = _movie

    fun getMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = Repo.getInstance().getMovie(id)
            Log.d("DetailsViewModel", "movie=$movie")
            _movie.value = movie
        }
    }
}