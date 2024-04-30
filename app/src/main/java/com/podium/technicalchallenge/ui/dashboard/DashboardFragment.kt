package com.podium.technicalchallenge.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.podium.technicalchallenge.DemoViewModel
import com.podium.technicalchallenge.entity.MovieEntity
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private val viewModel: DemoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                DashboardScreen(viewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()
    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(viewModel: DemoViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf("Movies: Top 5", "Browse by Genre", "Browse by All")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val movies = remember { mutableStateOf(emptyList<MovieEntity>()) }

    LaunchedEffect(viewModel.movies) {
        viewModel.movies.collect {newMovies ->
            movies.value = newMovies
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
                        0 -> MoviesContent(movies.value) {

                        }
                        1 -> MoviesContent(movies.value) {

                        }
                        2 -> MoviesContent(movies.value) {

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
            Text(text = "Movies")
        },
        actions = {

        }
    )
}

