package ir.digireza.tmdb.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.digireza.tmdb.data.datasources.remote.client.RestClient
import ir.digireza.tmdb.data.datasources.remote.client.RetrofitBase
import ir.digireza.tmdb.data.datasources.remote.client.TMDBServices
import ir.digireza.tmdb.data.datasources.remote.parser.JsonParser
import ir.digireza.tmdb.data.datasources.remote.services.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideMovieDataSource(
        restClient: RestClient,
        jsonParser: JsonParser
    ): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(restClient = restClient, jsonParser = jsonParser)


    @Singleton
    @Provides
    fun provideTvshowDataSource(
        restClient: RestClient,
        jsonParser: JsonParser
    ): TvshowRemoteDataSource =
        TvshowRemoteDataSourceImpl(restClient, jsonParser)

    @Singleton
    @Provides
    fun providePeopleDataSource(
        restClient: RestClient,
        jsonParser: JsonParser
    ): PeopleRemoteDataSource =
        PeopleRemoteDataSourceImpl(restClient, jsonParser)
}