package ir.digireza.tmdb.presentation.search.components

import ir.digireza.tmdb.domain.entity.Movie
import ir.digireza.tmdb.domain.entity.People
import ir.digireza.tmdb.domain.entity.Tvshow


sealed class SearchPageState {
    object InitialState : SearchPageState()
    object LoadingState : SearchPageState()
    data class ErrorState(val errorMsg: String) : SearchPageState()
    data class MoviesListState(val data: List<Movie>?) : SearchPageState()
    data class TvshowsListState(val data: List<Tvshow>?) : SearchPageState()
    data class PeopleListState(val data: List<People>?) : SearchPageState()
}

/*
data class SearchPageStates(
    val isLoading: Boolean = false,
    val movies: ArrayList<Movie>? = ArrayList() ,
    val errorMsg: String = ""
)*/
