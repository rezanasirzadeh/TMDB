package ir.digireza.tmdb.domain.repository

import androidx.paging.PagingData
import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.data.datasources.remote.parameters.MovieDetailsParam
import ir.digireza.tmdb.domain.entity.Movie
import ir.digireza.tmdb.domain.entity.MovieCredits
import ir.digireza.tmdb.domain.entity.MovieDetails
import ir.digireza.tmdb.domain.entity.MoviePictures
import kotlinx.coroutines.flow.Flow


interface MovieRepository {

    fun getPopularMovies(): Flow<PagingData<Movie>>

    suspend fun getFirstTenPopularMovies(): DataState<List<Movie>>
    suspend fun getFirstTenUpcomingMovies(): DataState<List<Movie>>
    suspend fun getFirstTenFeaturedMovies(): DataState<List<Movie>>

    fun getFeatureMoves(): Flow<PagingData<Movie>>

    fun searchMovie(query: String): Flow<PagingData<Movie>>

    suspend fun getMoviePrimaryDetails(movieDetailsParam: MovieDetailsParam): DataState<MovieDetails>
    suspend fun getMoviePictures(movieDetailsParam: MovieDetailsParam): DataState<List<MoviePictures>>
    suspend fun getMovieCredits(movieDetailsParam: MovieDetailsParam): DataState<List<MovieCredits>>

}