package ir.digireza.tmdb.data.paging.people

import ir.digireza.tmdb.data.datasources.remote.parameters.PagingParam
import ir.digireza.tmdb.data.datasources.remote.services.PeopleRemoteDataSource
import ir.digireza.tmdb.data.model.PeopleResponse
import ir.digireza.tmdb.data.paging.BasePagingSource
import ir.digireza.tmdb.domain.entity.People
import javax.inject.Inject

class PopularPeoplePagingSource @Inject constructor(private val peopleRemoteDataSource: PeopleRemoteDataSource): BasePagingSource<People>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {
        val position = params.key ?: 1

        return try{
            val response : PeopleResponse = peopleRemoteDataSource.getPopularPeople(PagingParam(position))
            val people: List<People> = response.toEntity()

            LoadResult.Page(
                data = people,
                prevKey = getPrevKey(position),
                nextKey = getNextKey(position, people)
            )
        }catch (error: Exception){
            LoadResult.Error(error)
        }
    }
}