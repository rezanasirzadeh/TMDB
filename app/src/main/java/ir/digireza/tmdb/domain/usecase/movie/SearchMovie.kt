package ir.digireza.tmdb.domain.usecase.movie

import androidx.paging.PagingData
import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.app.base.usecase.UseCase
import ir.digireza.tmdb.data.datasources.remote.parameters.PagingParam
import ir.digireza.tmdb.data.datasources.remote.parameters.SearchParam
import ir.digireza.tmdb.domain.entity.Movie
import ir.digireza.tmdb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovie @Inject constructor(private val movieRepository: MovieRepository) {

      operator fun invoke(query: String): Flow<PagingData<Movie>> =
        movieRepository.searchMovie(query)
}