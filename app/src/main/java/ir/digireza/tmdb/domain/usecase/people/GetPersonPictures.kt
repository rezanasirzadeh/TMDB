package ir.digireza.tmdb.domain.usecase.people

import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.data.datasources.remote.parameters.MovieDetailsParam
import ir.digireza.tmdb.data.datasources.remote.parameters.PersonDetailsParam
import ir.digireza.tmdb.domain.entity.PersonPictures
import ir.digireza.tmdb.domain.repository.PeopleRepository
import javax.inject.Inject

class GetPersonPictures @Inject constructor(
    private val peopleRepository: PeopleRepository
) {
    suspend operator fun invoke(personId: Int): List<String> {
        val response = peopleRepository.getPersonPictures(
            PersonDetailsParam(personId)
        )

        return if (response is DataState.DataSuccessState) response.data.map { it.picturePath }
        else emptyList()
    }

}