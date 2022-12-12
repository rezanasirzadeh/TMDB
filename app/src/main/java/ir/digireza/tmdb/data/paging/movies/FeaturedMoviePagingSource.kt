package ir.digireza.tmdb.data.paging.movies

import ir.digireza.tmdb.data.datasources.remote.parameters.PagingParam
import ir.digireza.tmdb.data.datasources.remote.services.MovieRemoteDataSource
import ir.digireza.tmdb.data.model.MovieResponse
import ir.digireza.tmdb.data.paging.BasePagingSource
import ir.digireza.tmdb.domain.entity.Movie
import javax.inject.Inject

class FeaturedMoviePagingSource @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : BasePagingSource<Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
       val position = params.key ?: 1

        return try {
            val response: MovieResponse = movieRemoteDataSource.getFeatureMoves(PagingParam(page = position))
            val movies: List<Movie> = response.toEntity()

            LoadResult.Page(
                data = movies,
                prevKey = getPrevKey(position),
                nextKey = getNextKey(position, movies)
            )


        } catch (error: Exception) {
            return LoadResult.Error(error)
        }
    }

}
