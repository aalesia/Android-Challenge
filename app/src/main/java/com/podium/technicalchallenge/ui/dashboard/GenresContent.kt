package com.podium.technicalchallenge.ui.dashboard

import androidx.compose.foundation.clickable
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
import com.podium.technicalchallenge.R

@Composable
internal fun GenresContent(
    genres: List<String>,
    onGenreClick: (String) -> Unit
) {
    LazyColumn {
        items(genres) { genre ->
            GenreItem(genre, onGenreClick)
        }
    }
}

@Composable
private fun GenreItem(
    genre: String,
    onGenreClick: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.horizontal_margin),
                vertical = dimensionResource(id = R.dimen.list_item_padding),
            )
            .clickable { onGenreClick(genre) }
    ) {
        Text(
            text = genre,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.horizontal_margin)
            )
        )
    }
}