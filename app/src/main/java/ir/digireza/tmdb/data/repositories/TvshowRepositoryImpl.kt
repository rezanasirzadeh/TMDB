package ir.digireza.tmdb.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.app.base.error.ServerException
import ir.digireza.tmdb.data.datasources.remote.parameters.PagingParam
import ir.digireza.tmdb.data.datasources.remote.parameters.SearchParam
import ir.digireza.tmdb.data.datasources.remote.services.TvshowRemoteDataSource
import ir.digireza.tmdb.data.paging.tvshow.FeaturedTvshowPagingSource
import ir.digireza.tmdb.data.paging.tvshow.PopularTvshowPagingSource
import ir.digireza.tmdb.data.paging.tvshow.SearchTvshowPagingSource
import ir.digireza.tmdb.domain.entity.Tvshow
import ir.digireza.tmdb.domain.repository.TvshowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvshowRepositoryImpl @Inject constructor(
    private val tvshowRemoteDataSource: TvshowRemoteDataSource,
    private val popularTvshowPagingSource: PopularTvshowPagingSource,
    private val featuredTvshowPagingSource: FeaturedTvshowPagingSource,
    private val pagingConfig: PagingConfig,
) :
    TvshowRepository {

    override   fun getPopularTvshows(): Flow<PagingData<Tvshow>> = Pager(pagingConfig) {
        popularTvshowPagingSource
    }.flow

    override   fun getFeaturedTvshows(): Flow<PagingData<Tvshow>> = Pager(pagingConfig){
        featuredTvshowPagingSource
    }.flow

    override suspend fun getFirstTenPopularTvshow(): DataState<List<Tvshow>> {
        return try {
            val res = tvshowRemoteDataSource.getPopularTvshows(PagingParam(1))
            DataState.DataSuccessState(data = res.toEntity().take(10))
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override suspend fun getFirstTenFeaturedTvshow(): DataState<List<Tvshow>> {
        return try {
            val res = tvshowRemoteDataSource.getFeatureTvshows(PagingParam(1))
            DataState.DataSuccessState(data = res.toEntity().take(10))
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override fun searchTvshows(query: String): Flow<PagingData<Tvshow>> = Pager(pagingConfig){
        SearchTvshowPagingSource(
            tvshowRemoteDataSource, query
        )
    }.flow
}