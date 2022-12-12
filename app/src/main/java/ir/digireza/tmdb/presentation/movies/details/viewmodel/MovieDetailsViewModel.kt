package ir.digireza.tmdb.presentation.movies.details.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.domain.entity.MovieCredits
import ir.digireza.tmdb.domain.entity.MovieDetails
import ir.digireza.tmdb.domain.entity.MoviePictures
import ir.digireza.tmdb.domain.usecase.movie.GetMovieCredits
import ir.digireza.tmdb.domain.usecase.movie.GetMoviePictures
import ir.digireza.tmdb.domain.usecase.movie.GetMoviePrimaryDetails
import ir.digireza.tmdb.presentation.movies.details.components.MovieCreditStates
import ir.digireza.tmdb.presentation.movies.details.components.MoviePicturesState
import ir.digireza.tmdb.presentation.movies.details.components.MoviePrimaryDetailsState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMoviePrimaryDetails: GetMoviePrimaryDetails,
    private val getMoviePictures: GetMoviePictures,
    private val getMovieCredits: GetMovieCredits,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private var _movieDetailsState = mutableStateOf(MoviePrimaryDetailsState())
    val movieDetailsState: MoviePrimaryDetailsState by _movieDetailsState

    private var _moviePicturesState = mutableStateOf(MoviePicturesState())
    val moviePicturesState: MoviePicturesState by _moviePicturesState

    private var _movieCreditState = mutableStateOf(MovieCreditStates())
    val movieCreditState: MovieCreditStates by _movieCreditState


    fun getMoviePrimaryDetails(movieId: Int) {
        _movieDetailsState.value = MoviePrimaryDetailsState(isLoading = true)
        viewModelScope.launch(dispatcher) {
            val response: DataState<MovieDetails> = getMoviePrimaryDetails.invoke(movieId)
            if (response is DataState.DataSuccessState)
                _movieDetailsState.value = MoviePrimaryDetailsState(movieDetails = response.data)
            else _movieDetailsState.value = MoviePrimaryDetailsState(hasError = true)
        }
    }

    fun getMoviePictures(movieId: Int) {
        _moviePicturesState.value = MoviePicturesState(isLoading = true)

        viewModelScope.launch(dispatcher) {
            val list: List<String> = getMoviePictures.invoke(movieId)
            _moviePicturesState.value = MoviePicturesState(moviePictures = list)
        }
    }

    fun getMovieCredits(movieId: Int) {
        _movieCreditState.value = MovieCreditStates(isLoading = true)

        viewModelScope.launch(dispatcher) {
            val response: DataState<List<MovieCredits>> = getMovieCredits.invoke(movieId)
            if (response is DataState.DataSuccessState)
                _movieCreditState.value = MovieCreditStates(movieCredits = response.data)
        }
    }
}