package ir.digireza.tmdb.presentation.movies.show_all.components

import ir.digireza.tmdb.domain.entity.Movie


data class AllMoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val errorMessage: String = ""
)