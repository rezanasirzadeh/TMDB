package ir.digireza.tmdb.domain.usecase.tvshow

import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.domain.entity.Tvshow
import ir.digireza.tmdb.domain.repository.TvshowRepository
import javax.inject.Inject

class GetFirstTenPopularTvshow @Inject constructor(private val tvshowRepository: TvshowRepository) {

    suspend operator fun  invoke(): DataState<List<Tvshow>> = tvshowRepository.getFirstTenPopularTvshow()
}