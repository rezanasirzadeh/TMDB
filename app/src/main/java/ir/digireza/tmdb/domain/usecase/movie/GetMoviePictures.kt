package ir.digireza.tmdb.domain.usecase.movie

import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.data.datasources.remote.parameters.MovieDetailsParam
import ir.digireza.tmdb.domain.entity.MoviePictures
import ir.digireza.tmdb.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviePictures @Inject constructor(val movieRepository: MovieRepository) {

    suspend operator fun invoke(movieId: Int): List<String> {
        val response = movieRepository.getMoviePictures(MovieDetailsParam(movieId))

        return if (response is DataState.DataSuccessState) response.data.map { it.posterPath }
        else emptyList()
    }

}