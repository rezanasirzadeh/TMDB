package ir.digireza.tmdb.domain.usecase.people

import androidx.paging.PagingData
import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.app.base.usecase.UseCase
import ir.digireza.tmdb.data.datasources.remote.parameters.SearchParam
import ir.digireza.tmdb.domain.entity.People
import ir.digireza.tmdb.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPeople  @Inject constructor(private val peopleRepository: PeopleRepository) {

    operator fun invoke(query: String): Flow<PagingData<People>> = peopleRepository.searchPeople(query)
}