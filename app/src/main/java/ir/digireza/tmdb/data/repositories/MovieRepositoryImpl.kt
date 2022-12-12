package ir.digireza.tmdb.data.repositories

import androidx.paging.*
import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.app.base.error.ServerException
import ir.digireza.tmdb.data.datasources.remote.parameters.MovieDetailsParam
import ir.digireza.tmdb.data.paging.movies.PopularMoviePagingSource
import ir.digireza.tmdb.data.datasources.remote.parameters.PagingParam
import ir.digireza.tmdb.data.datasources.remote.services.MovieRemoteDataSource
import ir.digireza.tmdb.data.model.MovieCreditsResponse
import ir.digireza.tmdb.data.model.MovieDetailsResponse
import ir.digireza.tmdb.data.model.MoviePicturesResponse
import ir.digireza.tmdb.data.model.MovieResponse
import ir.digireza.tmdb.data.paging.movies.FeaturedMoviePagingSource
import ir.digireza.tmdb.data.paging.movies.SearchMoviePagingSource
import ir.digireza.tmdb.domain.entity.Movie
import ir.digireza.tmdb.domain.entity.MovieCredits
import ir.digireza.tmdb.domain.entity.MovieDetails
import ir.digireza.tmdb.domain.entity.MoviePictures
import ir.digireza.tmdb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val popularMoviePagingSource: PopularMoviePagingSource,
    private val featuredMoviePagingSource: FeaturedMoviePagingSource,
    private val pageConfig: PagingConfig
) : MovieRepository {

    override fun getPopularMovies(): Flow<PagingData<Movie>> = Pager(pageConfig) {
        popularMoviePagingSource
    }.flow

    override suspend fun getFirstTenPopularMovies(): DataState<List<Movie>> {
        return try {
            val response: MovieResponse = movieRemoteDataSource.getPopularMovies(PagingParam(1))

            DataState.DataSuccessState(response.toEntity().take(10))
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override suspend fun getFirstTenUpcomingMovies(): DataState<List<Movie>> {
        return try {
            val response: MovieResponse = movieRemoteDataSource.getUpComingMovies(PagingParam(1))

            DataState.DataSuccessState(response.toEntity().take(10))
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override suspend fun getFirstTenFeaturedMovies(): DataState<List<Movie>> {
        return try {
            val response: MovieResponse = movieRemoteDataSource.getFeatureMoves(PagingParam(1))

            DataState.DataSuccessState(response.toEntity().take(10))
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }


    override fun getFeatureMoves(): Flow<PagingData<Movie>> = Pager(pageConfig) {
        featuredMoviePagingSource
    }.flow

    override fun searchMovie(query: String): Flow<PagingData<Movie>> = Pager(pageConfig) {
        SearchMoviePagingSource(movieRemoteDataSource, query)
    }.flow.catch {
        println("sth went wrong")
    }

    override suspend fun getMoviePrimaryDetails(movieDetailsParam: MovieDetailsParam): DataState<MovieDetails> {
        return try {
            val response: MovieDetailsResponse = movieRemoteDataSource.getMovieDetails(movieDetailsParam)

            DataState.DataSuccessState(response.toEntity())
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override suspend fun getMoviePictures(movieDetailsParam: MovieDetailsParam): DataState<List<MoviePictures>> {
        return try {
            val response: MoviePicturesResponse = movieRemoteDataSource.getMoviePictures(movieDetailsParam)

            DataState.DataSuccessState(response.toEntity())
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override suspend fun getMovieCredits(movieDetailsParam: MovieDetailsParam): DataState<List<MovieCredits>> {
        return try {
            val response: MovieCreditsResponse = movieRemoteDataSource.getMovieCredits(movieDetailsParam)

            DataState.DataSuccessState(response.toEntity())
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }
}