package ir.digireza.tmdb.domain.usecase.tvshow

import androidx.paging.PagingData
import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.app.base.usecase.UseCase
import ir.digireza.tmdb.data.datasources.remote.parameters.PagingParam
import ir.digireza.tmdb.domain.entity.Tvshow
import ir.digireza.tmdb.domain.repository.TvshowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFeaturedTvshows @Inject constructor(private val tvshowRepository: TvshowRepository) {

    operator fun invoke(): Flow<PagingData<Tvshow>> = tvshowRepository.getFeaturedTvshows()
}