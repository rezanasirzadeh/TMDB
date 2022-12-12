package ir.digireza.tmdb.presentation.dashboard

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.*
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import com.google.accompanist.pager.*
import ir.digireza.tmdb.app.constants.Constant
import ir.digireza.tmdb.app.constants.MoviesType
import ir.digireza.tmdb.app.constants.TvshowType
import ir.digireza.tmdb.app.extensions.navigation
import ir.digireza.tmdb.app.extensions.putEnumExtra
import ir.digireza.tmdb.data.datasources.remote.parameters.PersonDetailsParam
import ir.digireza.tmdb.domain.entity.Movie
import ir.digireza.tmdb.domain.entity.People
import ir.digireza.tmdb.domain.entity.Tvshow
import ir.digireza.tmdb.presentation.dashboard.components.*
import ir.digireza.tmdb.presentation.dashboard.viewmodel.DashboardViewModel
import ir.digireza.tmdb.presentation.movies.details.MovieDetailsPage
import ir.digireza.tmdb.presentation.movies.show_all.AllMoviesPage
import ir.digireza.tmdb.presentation.people.details.PersonDetailsPage
import ir.digireza.tmdb.presentation.people.show_all.AllPeoplePage
import ir.digireza.tmdb.presentation.shared_components.LoadingComponent
import ir.digireza.tmdb.presentation.shared_components.SpacerComponent
import ir.digireza.tmdb.presentation.tvshows.AllTvshowPage
import kotlin.math.absoluteValue


@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalPagerApi::class)
@Composable
fun DashboardPage(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val sliderPictures: List<Movie> = viewModel.upComingMovies
    val pagerState = rememberPagerState(pageCount = sliderPictures.size)

    Column(
        modifier = Modifier.verticalScroll(state = rememberScrollState())
    ) {
        AnimatedVisibility(visible = sliderPictures.isNotEmpty()) {
            Box(modifier = Modifier.height(600.dp)) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                ) { index ->

                    AsyncImage(
                        model = sliderPictures[index].posterPath,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(600.dp)
                            .graphicsLayer {
                                val pageOffset = calculateCurrentOffsetForPage(index).absoluteValue

                                lerp(
                                    start = 0.45f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                ).also { scale ->
                                    scaleX = scale
                                    scaleY = scale
                                }

                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            }
                    )

                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = MaterialTheme.colorScheme.primary,
                    inactiveColor = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp)
                )
            }

        }
        SpacerComponent(height = 32.dp)

        PopularMoviesList(viewModel.popularMovies, showAllTapped = {
            context.startActivity(
                context.navigation<AllMoviesPage>(
                    extras = mapOf<String, String>(
                        Constant.PAGE_TITLE_KEY to "Today Popular Movies"
                    )
                ).putEnumExtra<MoviesType>(enum = MoviesType.populraMovies)
            )
        }, onMovieClick = { movieId ->
            context.startActivity(
                context.navigation<MovieDetailsPage>(extras = mapOf(Constant.CLICKED_ITEM_ID to movieId.toString()))
            )
        })
        SpacerComponent(height = 24.dp)
        FeaturedMoviesList(viewModel.featuredMovies) {
            context.startActivity(
                context.navigation<AllMoviesPage>(
                    extras = mapOf<String, String>(
                        Constant.PAGE_TITLE_KEY to "Featured Movies"
                    )
                ).putEnumExtra<MoviesType>(enum = MoviesType.featuredMovies)
            )
        }
        SpacerComponent(height = 24.dp)
        UpcomingMoviesList(viewModel.upComingMovies) {
            context.startActivity(
                context.navigation<AllMoviesPage>(
                    extras = mapOf<String, String>(
                        Constant.PAGE_TITLE_KEY to "Upcoming Movies"
                    )
                ).putEnumExtra<MoviesType>(enum = MoviesType.upcomingMovies)
            )
        }
        SpacerComponent(height = 24.dp)
        PopularPeopleList(viewModel.popularPeople, showAllTapped = {
            context.startActivity(
                context.navigation<AllPeoplePage>(
                    extras = mapOf<String, String>(
                        Constant.PAGE_TITLE_KEY to "Today Popular People"
                    )
                )
            )
        }, onPersonClick = { personId ->
            context.startActivity(
                context.navigation<PersonDetailsPage>(
                    extras = mapOf(
                        Constant.CLICKED_ITEM_ID to personId.toString()
                    )
                )
            )
        })
        SpacerComponent(height = 24.dp)
        PopularTvshowList(viewModel.popoularTvshows) {
            context.startActivity(
                context.navigation<AllTvshowPage>(
                    extras = mapOf<String, String>(
                        Constant.PAGE_TITLE_KEY to "Today Popular Tv shows"
                    )
                ).putEnumExtra<TvshowType>(enum = TvshowType.populraTvshows)
            )
        }
        SpacerComponent(height = 24.dp)
        FeaturedTvshowList(viewModel.featuredTvshow) {
            context.startActivity(
                context.navigation<AllTvshowPage>(
                    extras = mapOf<String, String>(
                        Constant.PAGE_TITLE_KEY to "Featured Tv shows"
                    )
                ).putEnumExtra<TvshowType>(enum = TvshowType.featuredTvshows)
            )
        }
    }
}


@Composable
fun PopularMoviesList(
    list: List<Movie>, showAllTapped: () -> Unit, onMovieClick: (movieId: Int) -> Unit
) {

    HomeListHeader("Today Popular Movies") {
        showAllTapped()
    }
    SpacerComponent(height = 16.dp)
    if (list.isEmpty()) {
        LoadingComponent()
    } else {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = list) { movie ->
                FilmItem(
                    title = movie.title,
                    coverPhoto = movie.posterPath,
                    voteAverage = movie.voteAverage.toString()
                ) {
                    onMovieClick(movie.id)
                }
            }
        }
    }
}


@Composable
fun PopularPeopleList(
    list: List<People>, showAllTapped: () -> Unit, onPersonClick: (personId: Int) -> Unit
) {
    HomeListHeader("Today Popular People") {
        showAllTapped()
    }
    SpacerComponent(height = 16.dp)
    if (list.isEmpty()) {
        LoadingComponent()
    } else {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = list) { item ->
                PeopleItem(item) {
                    onPersonClick(item.id)
                }
            }
        }
    }

}


@Composable
fun UpcomingMoviesList(list: List<Movie>, showAllTapped: () -> Unit) {
    HomeListHeader("Upcoming Movies") { showAllTapped() }
    SpacerComponent(height = 16.dp)
    if (list.isEmpty()) {
        LoadingComponent()
    } else {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = list) { movie ->
                FilmItem(
                    title = movie.title,
                    coverPhoto = movie.posterPath,
                    voteAverage = movie.voteAverage.toString()
                ) {}
            }
        }
    }

}

@Composable
fun FeaturedMoviesList(list: List<Movie>, showAllTapped: () -> Unit) {
    HomeListHeader("Featured Movies") { showAllTapped() }
    SpacerComponent(height = 16.dp)
    if (list.isEmpty()) {
        LoadingComponent()
    } else {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = list) { movie ->
                FilmItem(
                    title = movie.title,
                    coverPhoto = movie.posterPath,
                    voteAverage = movie.voteAverage.toString()
                ) {}
            }
        }
    }

}


@Composable
fun PopularTvshowList(list: List<Tvshow>, showAllTapped: () -> Unit) {
    HomeListHeader("Popular Tv Shows") { showAllTapped() }
    SpacerComponent(height = 16.dp)
    if (list.isEmpty()) {
        LoadingComponent()
    } else {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = list) { movie ->
                FilmItem(
                    title = movie.name,
                    coverPhoto = movie.posterPath,
                    voteAverage = movie.voteAverage.toString()
                ) {}
            }
        }
    }
}


@Composable
fun FeaturedTvshowList(list: List<Tvshow>, showAllTapped: () -> Unit) {
    HomeListHeader("Featured TV Shows") { showAllTapped() }
    SpacerComponent(height = 16.dp)
    if (list.isEmpty()) {
        LoadingComponent()
    } else {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = list) { movie ->
                FilmItem(
                    title = movie.name,
                    coverPhoto = movie.posterPath,
                    voteAverage = movie.voteAverage.toString()
                ) {}
            }
        }
    }
}