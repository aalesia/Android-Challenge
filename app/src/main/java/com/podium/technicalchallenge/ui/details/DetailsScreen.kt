package com.podium.technicalchallenge.ui.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
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
    val movie = remember { mutableStateOf(MovieEntity(movieId, "", "", "", 0f, "")) }

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
                    Column {
                        AsyncImage(
                            model = movie.value.posterPath,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(
                                    vertical = dimensionResource(id = R.dimen.vertical_margin)
                                )
                                .align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = movie.value.title,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(
                                bottom = dimensionResource(id = R.dimen.vertical_margin)
                            )
                        )
                        Text(
                            text = movie.value.overview,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(
                                bottom = dimensionResource(id = R.dimen.vertical_margin)
                            )
                        )
                        Text(
                            text = stringResource(R.string.rating, movie.value.voteAverage),
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(
                                bottom = dimensionResource(id = R.dimen.vertical_margin)
                            )
                        )
                        movie.value.genres?.let { genres ->
                            Text(
                                text = stringResource(R.string.genres, genres.joinToString(", ")),
                                style = MaterialTheme.typography.body2,
                                modifier = Modifier.padding(
                                    bottom = dimensionResource(id = R.dimen.vertical_margin)
                                )
                            )
                        }
                        movie.value.director?.let { director ->
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
                movie.value.cast?.let { cast ->
                    items(cast) { castMember ->
                        CastItem(castMember)
                    }
                }
            }
        }
    )
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
        },
        actions = {

        }
    )
}