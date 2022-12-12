package ir.digireza.tmdb.domain.usecase.movie

import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.data.datasources.remote.parameters.MovieDetailsParam
import ir.digireza.tmdb.domain.entity.MovieCredits
import ir.digireza.tmdb.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieCredits @Inject constructor(val movieRepository: MovieRepository) {

    suspend operator fun invoke(movieId: Int): DataState<List<MovieCredits>> =
        movieRepository.getMovieCredits(MovieDetailsParam(movieId))

}