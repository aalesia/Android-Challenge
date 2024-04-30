package com.podium.technicalchallenge.ui.details

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
import com.podium.technicalchallenge.ui.shared.MoviesContent
import com.podium.technicalchallenge.viewmodel.DetailsViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, movieId: Int) {
    val viewModel: DetailsViewModel = viewModel()
    val movie = remember { mutableStateOf(MovieEntity(movieId, "", "", "")) }

    LaunchedEffect(key1 = true) {
        viewModel.getMovie(movie.value.id)
    }

    LaunchedEffect(viewModel.movie) {
        viewModel.movie.collect { newMovie ->
            movie.value = newMovie
        }
    }

    Scaffold(
        topBar = { DetailsAppBar(navController) },
        content = {
            MoviesContent(listOf(movie.value)) {

            }
        }
    )
}

@Composable
fun DetailsAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(text = "Details")
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