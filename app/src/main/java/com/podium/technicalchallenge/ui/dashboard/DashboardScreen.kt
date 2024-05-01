package com.podium.technicalchallenge.ui.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.viewmodel.DemoViewModel
import com.podium.technicalchallenge.entity.MovieEntity
import com.podium.technicalchallenge.ui.navigation.Screen
import com.podium.technicalchallenge.ui.shared.MoviesContent
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(navController: NavController) {
    val viewModel: DemoViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf(
        stringResource(R.string.movies_top_5),
        stringResource(R.string.browse_by_genre),
        stringResource(R.string.browse_by_all)
    )
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val movies = remember { mutableStateOf(emptyList<MovieEntity>()) }
    val top5Movies = remember { mutableStateOf(emptyList<MovieEntity>()) }
    val genres = remember { mutableStateOf(emptyList<String>()) }

    LaunchedEffect(key1 = true) {
        viewModel.getMovies()
        viewModel.getTop5Movies()
        viewModel.getGenres()
    }

    LaunchedEffect(viewModel.movies) {
        viewModel.movies.collect { newMovies ->
            movies.value = newMovies
        }
    }

    LaunchedEffect(viewModel.top5Movies) {
        viewModel.top5Movies.collect { newMovies ->
            top5Movies.value = newMovies
        }
    }

    LaunchedEffect(viewModel.genres) {
        viewModel.genres.collect { newGenres ->
            genres.value = newGenres
        }
    }

    Scaffold(
        topBar = { MoviesAppBar() },
        content = {
            Column {
                TabRow(selectedTabIndex = pagerState.currentPage) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            }
                        )
                    }
                }

                HorizontalPager(state = pagerState) { page ->
                    when (page) {
                        0 -> MoviesContent(top5Movies.value) { movie ->
                            navController.navigate(Screen.Details.withId(movie.id))
                        }
                        1 -> GenresContent(genres.value) { genre ->
                            navController.navigate(Screen.Genre.withGenre(genre))
                        }
                        2 -> MoviesContent(movies.value) { movie ->
                            navController.navigate(Screen.Details.withId(movie.id))
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun MoviesAppBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.movies))
        },
        actions = {

        }
    )
}

