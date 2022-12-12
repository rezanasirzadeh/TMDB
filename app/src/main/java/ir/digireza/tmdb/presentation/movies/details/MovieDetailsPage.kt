package ir.digireza.tmdb.presentation.movies.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import ir.digireza.tmdb.app.constants.Constant
import ir.digireza.tmdb.domain.entity.MovieDetails
import ir.digireza.tmdb.presentation.movies.details.components.HeaderDetailsComponent
import ir.digireza.tmdb.presentation.movies.details.viewmodel.MovieDetailsViewModel
import ir.digireza.tmdb.presentation.shared_components.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsPage : ComponentActivity() {

    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieId = intent.getStringExtra(Constant.CLICKED_ITEM_ID) ?: "0"


        lifecycleScope.launchWhenStarted {
            viewModel.getMoviePrimaryDetails((movieId.toInt()))
            viewModel.getMoviePictures((movieId.toInt()))
            viewModel.getMovieCredits((movieId.toInt()))
        }

        setContent {
            Box {
                DetailsBody(viewModel)
                IconButton(
                    modifier = Modifier.align(Alignment.TopStart),
                    onClick = { onBackPressedDispatcher.onBackPressed() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
                AnimatedVisibility(visible = viewModel.movieDetailsState.isLoading) {
                    LoadingComponent()
                }
            }
        }
    }
}

@Composable
fun DetailsBody(viewModel: MovieDetailsViewModel) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        viewModel.movieDetailsState.movieDetails?.let {
            HeaderDetailsComponent(
                it.posterPath,
                it.voteAvg.toString(),
                it.voteCount.toString(),
                it.status,
            )
        }
        SpacerComponent(height = 40.dp)
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                viewModel.movieDetailsState.movieDetails?.let { PrimaryMovieDetailsSection(it) }
            }
            Row(modifier = Modifier.padding(horizontal = 24.dp)) { DetailsHeadlineTextComponent("Pictures") }
            SpacerComponent(height = 10.dp)
            DetailsPictureItemComponent(
                viewModel.moviePicturesState.moviePictures,
                viewModel.moviePicturesState.isLoading
            )
            SpacerComponent(height = 30.dp)
            Row(modifier = Modifier.padding(horizontal = 24.dp)) { DetailsHeadlineTextComponent("Cast & Crew") }
            SpacerComponent(height = 10.dp)
            DetailsCreditsItemComponent(viewModel.movieCreditState)
            SpacerComponent(height = 30.dp)
            Row(modifier = Modifier.padding(horizontal = 24.dp)) { DetailsHeadlineTextComponent("Companies") }
            SpacerComponent(height = 10.dp)
            viewModel.movieDetailsState.movieDetails?.let { DetailsCompanyItemComponent(it) }
            SpacerComponent(height = 30.dp)
        }

    }
}

@Composable
fun PrimaryMovieDetailsSection(movie: MovieDetails) {
    Text(
        text = movie.originalTitle,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
    )
    SpacerComponent(height = 10.dp)
    Row {
        Text(
            text = movie.releaseDate,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        SpacerComponent(width = 10.dp)
        Text(
            text = movie.productionCounties.first(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        SpacerComponent(width = 10.dp)
        Text(
            text = movie.runtime.toString(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        SpacerComponent(width = 10.dp)
    }
    SpacerComponent(height = 10.dp)
    LazyRow {
        items(items = movie.genres) { genre ->
            OvalTextItemComponent(genre)
        }
    }
    SpacerComponent(height = 30.dp)
    DetailsHeadlineTextComponent("Spoken Languages")
    SpacerComponent(height = 10.dp)
    LazyRow {
        items(items = movie.spokenLanguages) { lang ->
            OvalTextItemComponent(lang)
        }
    }
    SpacerComponent(height = 30.dp)
    DetailsHeadlineTextComponent("Plot Summary")
    SpacerComponent(height = 10.dp)
    Text(
        text = movie.overview,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground
    )
    SpacerComponent(height = 30.dp)

}