package ir.digireza.tmdb.data.repositories

import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.app.base.error.ServerException
import ir.digireza.tmdb.data.datasources.remote.services.GenreRemoteDataSource
import ir.digireza.tmdb.data.model.GenreResponse
import ir.digireza.tmdb.domain.entity.Genre
import ir.digireza.tmdb.domain.repository.GenreRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(private val remoteDataSource: GenreRemoteDataSource): GenreRepository {
    override suspend fun getMovieGenres(): DataState<List<Genre>> {
        return try{
            val response: GenreResponse = remoteDataSource.getMovieGenres()
            DataState.DataSuccessState(data = response.toEntity())
        }catch (error: ServerException){
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override suspend fun getTvshowGenres(): DataState<List<Genre>> {
        return try{
            val response: GenreResponse = remoteDataSource.getTvshowGenres()
            DataState.DataSuccessState(data = response.toEntity())
        }catch (error: ServerException){
            DataState.DataFailedState(error.errorMessage)
        }
    }
}