package ir.digireza.tmdb.data.paging.tvshow

import ir.digireza.tmdb.data.datasources.remote.parameters.PagingParam
import ir.digireza.tmdb.data.datasources.remote.services.TvshowRemoteDataSource
import ir.digireza.tmdb.data.model.TvshowResponse
import ir.digireza.tmdb.data.paging.BasePagingSource
import ir.digireza.tmdb.domain.entity.Tvshow
import javax.inject.Inject

class FeaturedTvshowPagingSource @Inject constructor(private val tvshowRemoteDataSource: TvshowRemoteDataSource) :
    BasePagingSource<Tvshow>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Tvshow> {
        val position = params.key ?: 1

        return try {
            val response: TvshowResponse =
                tvshowRemoteDataSource.getFeatureTvshows(PagingParam(position))
            val tvshows: List<Tvshow> = response.toEntity()

            LoadResult.Page(
                data = tvshows,
                prevKey = getPrevKey(position),
                nextKey = getNextKey(position, tvshows)
            )
        } catch (error: Exception) {
            LoadResult.Error(error)
        }
    }
}