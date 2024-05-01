package com.podium.technicalchallenge.ui.details

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.entity.MovieEntity
import com.podium.technicalchallenge.viewmodel.DetailsViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, movieId: Int) {
    val viewModel: DetailsViewModel = viewModel()
    val movie = remember { mutableStateOf(MovieEntity(movieId, "", "", "", 0f, 0f)) }
    val isDialogOpen = remember { mutableStateOf(false) }

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
            LazyColumn(
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.horizontal_margin)
                    )
            ) {
                item {
                    MovieDetails(movie.value, isDialogOpen)
                }
                movie.value.cast?.let { cast ->
                    items(cast) { castMember ->
                        CastItem(castMember)
                    }
                }
            }
            if (isDialogOpen.value) {
                ImageDialog(isDialogOpen = isDialogOpen, movie = movie.value)
            }
        }
    )
}

@Composable
fun MovieDetails(movie: MovieEntity, isDialogOpen: MutableState<Boolean>) {
    Column {
        AsyncImage(
            model = movie.posterPath,
            contentDescription = null,
            modifier = Modifier
                .padding(
                    vertical = dimensionResource(id = R.dimen.vertical_margin)
                )
                .align(Alignment.CenterHorizontally)
                .clickable { isDialogOpen.value = true }
        )
        Text(
            text = movie.title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                bottom = dimensionResource(id = R.dimen.vertical_margin)
            )
        )
        movie.overview?.let { overview ->
            Text(
                text = overview,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(
                    bottom = dimensionResource(id = R.dimen.vertical_margin)
                )
            )
        }
        Text(
            text = stringResource(R.string.release_date, movie.releaseDate),
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(
                bottom = dimensionResource(id = R.dimen.vertical_margin)
            )
        )
        Text(
            text = stringResource(R.string.rating, movie.voteAverage),
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(
                bottom = dimensionResource(id = R.dimen.vertical_margin)
            )
        )
        Text(
            text = stringResource(R.string.popularity, movie.popularity),
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(
                bottom = dimensionResource(id = R.dimen.vertical_margin)
            )
        )
        movie.genres?.let { genres ->
            Text(
                text = stringResource(R.string.genres, genres.joinToString(", ")),
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(
                    bottom = dimensionResource(id = R.dimen.vertical_margin)
                )
            )
        }
        movie.director?.let { director ->
            Text(
                text = stringResource(R.string.director, director.name),
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(
                    bottom = dimensionResource(id = R.dimen.vertical_margin)
                )
            )
        }
    }
}

@Composable
fun ImageDialog(isDialogOpen: MutableState<Boolean>, movie: MovieEntity) {
    if (isDialogOpen.value) {
        Dialog(onDismissRequest = { isDialogOpen.value = false }) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = movie.posterPath,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(onClick = { isDialogOpen.value = false },
                    modifier = Modifier.align(Alignment.TopEnd)) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }
        }
    }
}

@Composable
fun DetailsAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.details))
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}