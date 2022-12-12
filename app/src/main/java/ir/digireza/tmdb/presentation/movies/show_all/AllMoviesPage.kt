package ir.digireza.tmdb.presentation.movies.show_all

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dagger.hilt.android.AndroidEntryPoint
import ir.digireza.tmdb.app.constants.Constant
import ir.digireza.tmdb.app.constants.MoviesType
import ir.digireza.tmdb.app.extensions.getEnumExtra
import ir.digireza.tmdb.domain.entity.Movie
import ir.digireza.tmdb.presentation.movies.show_all.viewmodel.AllMoviesViewModel
import ir.digireza.tmdb.presentation.shared_components.LandFilmItemComponent


@AndroidEntryPoint
class AllMoviesPage : ComponentActivity() {

    private val allMoviesViewModel: AllMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pageTitle: String = intent?.getStringExtra(Constant.PAGE_TITLE_KEY) ?: ""
        val bodyContentType: MoviesType = intent?.getEnumExtra<MoviesType>() ?: MoviesType.featuredMovies

        allMoviesViewModel.getBodyData(bodyContentType)

        setContent {
            BodyContent(pageTitle, viewModel = allMoviesViewModel) {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyContent(
    pageTitle: String,
    viewModel: AllMoviesViewModel,
    onBackPressed: () -> Unit
) {
    val movies: LazyPagingItems<Movie> = viewModel.moviesPager.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = pageTitle) },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(movies) { item ->
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


        // TODO: Manage all types of UI for each these states
        when (movies.loadState.append) {
            is LoadState.NotLoading -> Log.d("AllMoviesPage", "Load stat is NOTHING")
            is LoadState.Error -> Log.d("AllMoviesPage", "Load stat is Error")
            else -> Log.d("AllMoviesPage", "Load stat is LOADING")
        }
    }
}


@Preview
@Composable
fun Bodys() {
}