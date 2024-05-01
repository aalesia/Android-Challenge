package com.podium.technicalchallenge.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.entity.CastEntity

@Composable
fun CastItem(
    castMember: CastEntity
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(id = R.dimen.list_item_padding),
            )
    ) {
        castMember.profilePath?.let { profilePath ->
            AsyncImage(
                model = profilePath,
                contentDescription = null,
            )
        }

        Column {
            Text(
                text = castMember.name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.horizontal_margin)
                )
            )
            Text(
                text = castMember.character,
                style = MaterialTheme.typography.body2,
                maxLines = 4,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.horizontal_margin)
                )
            )
        }
    }
}