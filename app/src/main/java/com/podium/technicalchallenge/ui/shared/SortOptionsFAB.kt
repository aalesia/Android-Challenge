package com.podium.technicalchallenge.ui.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.enums.SortOption

@Composable
fun SortOptionsFAB(
    fabVisible: MutableState<Boolean>,
    onClick: (SortOption) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column {
        AnimatedVisibility(visible = expanded.value) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.vertical_margin)),
            ) {
                SortOption.entries.forEach { option ->
                    ExtendedFloatingActionButton(
                        text = {
                            Text(text = option.displayName(context))
                        },
                        onClick = {
                            expanded.value = false
                            onClick(option)
                        }
                    )
                }
            }
        }
        AnimatedVisibility(visible = fabVisible.value) {
            ExtendedFloatingActionButton(
                text = {
                    Text(text = stringResource(R.string.sort))
                },
                onClick = {
                    expanded.value = expanded.value.not()
                }
            )
        }
    }
}