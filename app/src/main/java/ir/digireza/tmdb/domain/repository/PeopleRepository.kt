package ir.digireza.tmdb.domain.repository

import androidx.paging.PagingData
import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.data.datasources.remote.parameters.PersonDetailsParam
import ir.digireza.tmdb.domain.entity.People
import ir.digireza.tmdb.domain.entity.PersonPictures
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
     fun getPopularPeople(): Flow<PagingData<People>>

    suspend fun getFirstTenPopularPeople(): DataState<List<People>>

    fun searchPeople(query: String): Flow<PagingData<People>>

    suspend fun getPersonDetails(detailsParam: PersonDetailsParam): DataState<People>
    suspend fun getPersonPictures(detailsParam: PersonDetailsParam):DataState<List<PersonPictures>>

}