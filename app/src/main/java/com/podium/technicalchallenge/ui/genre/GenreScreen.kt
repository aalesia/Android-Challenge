package com.podium.technicalchallenge.ui.genre

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.podium.technicalchallenge.entity.MovieEntity
import com.podium.technicalchallenge.ui.navigation.Screen
import com.podium.technicalchallenge.ui.shared.MoviesContent
import com.podium.technicalchallenge.viewmodel.GenreViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GenreScreen(navController: NavController, genre: String) {
    val viewModel: GenreViewModel = viewModel()
    val movies = remember { mutableStateOf(emptyList<MovieEntity>()) }

    LaunchedEffect(key1 = true) {
        viewModel.getMovies(genre)
    }

    LaunchedEffect(viewModel.movies) {
        viewModel.movies.collect { newMovies ->
            movies.value = newMovies
        }
    }

    Scaffold(
        topBar = { GenreAppBar(navController, genre) },
        content = {
            MoviesContent(movies.value) { movie ->
                navController.navigate(Screen.Details.withId(movie.id))
            }
        }
    )
}

@Composable
fun GenreAppBar(navController: NavController, genre: String) {
    TopAppBar(
        title = {
            Text(text = genre)
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {

        }
    )
}