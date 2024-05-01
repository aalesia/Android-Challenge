package com.podium.technicalchallenge.ui.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.entity.MovieEntity

@Composable
internal fun MoviesContent(
    movies: List<MovieEntity>,
    onMovieClick: (MovieEntity) -> Unit
) {
    LazyColumn {
        items(movies) { movie ->
            MovieItem(movie, onMovieClick)
        }
    }
}

@Composable
private fun MovieItem(
    movie: MovieEntity,
    onMovieClick: (MovieEntity) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.horizontal_margin),
                vertical = dimensionResource(id = R.dimen.list_item_padding),
            )
            .clickable { onMovieClick(movie) }
    ) {
        AsyncImage(
            model = movie.posterPath,
            contentDescription = null,
        )

        Column {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.horizontal_margin)
                )
            )
            Text(
                text = stringResource(R.string.release_date, movie.releaseDate),
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.horizontal_margin)
                )
            )
            Text(
                text = stringResource(R.string.rating, movie.voteAverage),
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.horizontal_margin)
                )
            )
            Text(
                text = stringResource(R.string.popularity, movie.popularity),
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.horizontal_margin)
                )
            )
        }
    }
}