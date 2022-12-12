package ir.digireza.tmdb.di

import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.digireza.tmdb.data.datasources.remote.services.MovieRemoteDataSource
import ir.digireza.tmdb.data.paging.movies.FeaturedMoviePagingSource
import ir.digireza.tmdb.data.paging.movies.PopularMoviePagingSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PagingModule {

    @Singleton
    @Provides
    fun providePageConfig(): PagingConfig = PagingConfig(pageSize = 20)

}