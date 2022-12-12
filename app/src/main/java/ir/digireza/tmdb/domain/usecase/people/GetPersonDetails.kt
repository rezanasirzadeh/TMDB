package ir.digireza.tmdb.domain.usecase.people

import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.data.datasources.remote.parameters.PersonDetailsParam
import ir.digireza.tmdb.domain.entity.People
import ir.digireza.tmdb.domain.repository.PeopleRepository
import javax.inject.Inject

class GetPersonDetails @Inject constructor(
    private val peopleRepository: PeopleRepository
) {
    suspend operator fun invoke(personId: Int):
            DataState<People> = peopleRepository.getPersonDetails(
        PersonDetailsParam(personId)
    )
}