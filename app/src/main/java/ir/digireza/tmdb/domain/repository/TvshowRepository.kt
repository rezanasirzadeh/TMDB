package ir.digireza.tmdb.domain.repository

import androidx.paging.PagingData
import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.data.datasources.remote.parameters.PagingParam
import ir.digireza.tmdb.data.datasources.remote.parameters.SearchParam
import ir.digireza.tmdb.domain.entity.Tvshow
import kotlinx.coroutines.flow.Flow

interface TvshowRepository {

    fun getPopularTvshows(): Flow<PagingData<Tvshow>>

    fun getFeaturedTvshows(): Flow<PagingData<Tvshow>>

    suspend fun getFirstTenPopularTvshow(): DataState<List<Tvshow>>

    suspend fun getFirstTenFeaturedTvshow(): DataState<List<Tvshow>>

    fun searchTvshows(query: String): Flow<PagingData<Tvshow>>
}