package ir.digireza.tmdb.presentation.search

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import ir.digireza.tmdb.domain.entity.Movie
import ir.digireza.tmdb.domain.entity.People
import ir.digireza.tmdb.domain.entity.Tvshow
import ir.digireza.tmdb.presentation.search.components.*
import ir.digireza.tmdb.presentation.search.viewmodel.SearchViewModel
import ir.digireza.tmdb.presentation.shared_components.LandFilmItemComponent
import ir.digireza.tmdb.presentation.shared_components.LandPeopleItemComponent
import ir.digireza.tmdb.presentation.shared_components.LoadingComponent
import ir.digireza.tmdb.presentation.shared_components.SpacerComponent

@Composable
fun SearchPage(
    viewModel: SearchViewModel = hiltViewModel()
) {


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBarComponent(viewModel.searchText, onTextChange = { viewModel.searchText = it })
        SearchTopTabBarComponent(
            viewModel.selectedItemIndex,
            tabItems = viewModel.tabItems
        ) { viewModel.onTabSelected(it) }
        SpacerComponent(height = 16.dp)
        AnimatedVisibility(visible = viewModel.isLoading) {
            LoadingComponent()
        }
        SearchBody(
            viewModel.selectedItemIndex,
            viewModel.searchMovie().collectAsLazyPagingItems(),
            viewModel.searchTvshow().collectAsLazyPagingItems(),
            viewModel.searchPeople().collectAsLazyPagingItems(),
        )
    }
}

@Composable
fun MovieSearchBody(movies: LazyPagingItems<Movie>) {

    LazyColumn() {
        items(items = movies) { item ->
            item?.let {
                LandFilmItemComponent(
                    photo = item.posterPath,
                    name = item.title,
                    vote = item.voteAverage.toString(),
                    overview = item.overview
                )
            }
        }
    }
}

@Composable
fun TvShowSearchBody(tvshows: LazyPagingItems<Tvshow>) {
    LazyColumn() {
        items(items = tvshows) { item ->
            item?.let {
                LandFilmItemComponent(
                    photo = item.posterPath,
                    name = item.name,
                    vote = item.voteAverage.toString(),
                    overview = item.overview
                )
            }

        }
    }
}

@Composable
fun PeopleSearchBody(people: LazyPagingItems<People>) {
    LazyColumn() {
        items(people) { item ->
            item?.let {
                LandPeopleItemComponent(
                    photo = item.profilePath ?: "",
                    name = item.name,
                    popularity = item.popularity.toString(),
                    knownFor = item.knownFor
                )
            }

        }
    }
}

@Composable
fun SearchBody(
    selectedTabIndex: Int,
    movies: LazyPagingItems<Movie>,
    tvshows: LazyPagingItems<Tvshow>,
    people: LazyPagingItems<People>
) {

    when (selectedTabIndex) {
        0 -> MovieSearchBody(movies = movies)
        1 -> TvShowSearchBody(tvshows = tvshows)
        2 -> PeopleSearchBody(people = people)
        else -> {}
    }
}
