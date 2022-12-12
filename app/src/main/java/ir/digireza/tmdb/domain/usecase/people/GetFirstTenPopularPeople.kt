package ir.digireza.tmdb.domain.usecase.people

import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.domain.entity.People
import ir.digireza.tmdb.domain.repository.PeopleRepository
import javax.inject.Inject

class GetFirstTenPopularPeople @Inject constructor(private val peopleRepository: PeopleRepository) {

    suspend operator fun invoke(): DataState<List<People>> = peopleRepository.getFirstTenPopularPeople()
}