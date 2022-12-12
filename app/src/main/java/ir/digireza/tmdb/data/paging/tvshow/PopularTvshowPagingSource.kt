package ir.digireza.tmdb.data.paging.tvshow

import ir.digireza.tmdb.data.datasources.remote.parameters.PagingParam
import ir.digireza.tmdb.data.datasources.remote.services.TvshowRemoteDataSource
import ir.digireza.tmdb.data.model.TvshowResponse
import ir.digireza.tmdb.data.paging.BasePagingSource
import ir.digireza.tmdb.domain.entity.Tvshow
import javax.inject.Inject

class PopularTvshowPagingSource @Inject constructor(
    private val tvshowRemoteDataSource: TvshowRemoteDataSource,
) : BasePagingSource<Tvshow>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Tvshow> {
        val position = params.key ?: 1

        return try {
            val response: TvshowResponse = tvshowRemoteDataSource.getPopularTvshows(PagingParam(1))
            val tvshows = response.toEntity()
            LoadResult.Page(
                data = tvshows,
                prevKey = if (position == 1) null else position,
                nextKey = if (tvshows.isNotEmpty()) position + 1 else null,
            )
        } catch (error: Exception) {
            LoadResult.Error(error)
        }
    }
}