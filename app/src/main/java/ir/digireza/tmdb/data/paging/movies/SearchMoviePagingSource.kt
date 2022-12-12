package ir.digireza.tmdb.data.paging.movies

import android.util.Log
import androidx.paging.PagingSource
import ir.digireza.tmdb.data.datasources.remote.parameters.SearchParam
import ir.digireza.tmdb.data.datasources.remote.services.MovieRemoteDataSource
import ir.digireza.tmdb.data.model.MovieResponse
import ir.digireza.tmdb.data.paging.BasePagingSource
import ir.digireza.tmdb.domain.entity.Movie
import javax.inject.Inject

class SearchMoviePagingSource  @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val query: String
) : BasePagingSource<Movie>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        println("TMDB query is: $query")
        val position = params.key ?: 1
        return try {
            val response: MovieResponse = movieRemoteDataSource.searchMovie(SearchParam(position, query))
            val movies = response.toEntity()
            LoadResult.Page(
                data = movies,
                prevKey = if (position == 1) null else position,
                nextKey = if (movies.isNotEmpty()) position + 1 else null,
            )
        } catch (error: Exception) {
            LoadResult.Error(error)
        }
    }
}