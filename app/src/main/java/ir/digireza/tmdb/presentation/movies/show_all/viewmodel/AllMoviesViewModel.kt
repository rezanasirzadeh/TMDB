package ir.digireza.tmdb.presentation.movies.show_all.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.digireza.tmdb.app.constants.MoviesType
import ir.digireza.tmdb.domain.entity.Movie
import ir.digireza.tmdb.domain.usecase.movie.GetFeaturedMovies
import ir.digireza.tmdb.domain.usecase.movie.GetPopularMovies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@HiltViewModel
class AllMoviesViewModel @Inject constructor(
    private val getPopularMovies: GetPopularMovies,
    private val getFeaturedMovies: GetFeaturedMovies,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private var _moviesPager: Flow<PagingData<Movie>> =
        MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val moviesPager: Flow<PagingData<Movie>> get() = _moviesPager



    fun getBodyData(bodyContentType: MoviesType) {
        when (bodyContentType) {
            MoviesType.populraMovies -> getPopularMovies()
            else -> getFeaturedMovies()
        }
    }


    private fun getPopularMovies() {
        _moviesPager = getPopularMovies.invoke()
            .flowOn(dispatcher)
            .cachedIn(viewModelScope)
    }


    private fun getFeaturedMovies() {

        _moviesPager = getFeaturedMovies.invoke()
            .flowOn(dispatcher)
            .cachedIn(viewModelScope)
    }


}