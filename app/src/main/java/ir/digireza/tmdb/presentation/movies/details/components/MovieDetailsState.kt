package ir.digireza.tmdb.presentation.movies.details.components

import ir.digireza.tmdb.domain.entity.MovieCredits
import ir.digireza.tmdb.domain.entity.MovieDetails
import ir.digireza.tmdb.domain.entity.MoviePictures

data class MoviePrimaryDetailsState(
    val isLoading: Boolean = false,
    val movieDetails: MovieDetails? = null ,
    val hasError: Boolean = false
)
data class MoviePicturesState(
    val isLoading: Boolean = false,
    val  moviePictures: List<String> = emptyList(),
)
data class MovieCreditStates(
    val isLoading: Boolean = false,
    val  movieCredits: List<MovieCredits> = emptyList(),
)