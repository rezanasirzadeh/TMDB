package ir.digireza.tmdb.domain.usecase.movie

import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.domain.entity.Movie
import ir.digireza.tmdb.domain.repository.MovieRepository
import javax.inject.Inject

class GetFirstTenFeaturedMovies @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(): DataState<List<Movie>> = movieRepository.getFirstTenFeaturedMovies()
}