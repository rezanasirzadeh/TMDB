package ir.digireza.tmdb.di

import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.digireza.tmdb.data.datasources.remote.services.MovieRemoteDataSource
import ir.digireza.tmdb.data.datasources.remote.services.PeopleRemoteDataSource
import ir.digireza.tmdb.data.datasources.remote.services.TvshowRemoteDataSource
import ir.digireza.tmdb.data.paging.movies.FeaturedMoviePagingSource
import ir.digireza.tmdb.data.paging.movies.PopularMoviePagingSource
import ir.digireza.tmdb.data.paging.people.PopularPeoplePagingSource
import ir.digireza.tmdb.data.paging.tvshow.FeaturedTvshowPagingSource
import ir.digireza.tmdb.data.paging.tvshow.PopularTvshowPagingSource
import ir.digireza.tmdb.data.repositories.MovieRepositoryImpl
import ir.digireza.tmdb.data.repositories.PeopleRepositoryImpl
import ir.digireza.tmdb.data.repositories.TvshowRepositoryImpl
import ir.digireza.tmdb.domain.repository.MovieRepository
import ir.digireza.tmdb.domain.repository.PeopleRepository
import ir.digireza.tmdb.domain.repository.TvshowRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        popularMoviePagingSource: PopularMoviePagingSource,
        pageConfig: PagingConfig,
        featuredMoviePagingSource: FeaturedMoviePagingSource
    ): MovieRepository =
        MovieRepositoryImpl(
            movieRemoteDataSource = movieRemoteDataSource,
            popularMoviePagingSource = popularMoviePagingSource,
            pageConfig = pageConfig,
            featuredMoviePagingSource = featuredMoviePagingSource
        )

    @Singleton
    @Provides
    fun provideTvshowRepository(
        tvshowRemoteDataSource: TvshowRemoteDataSource,
        popularTvshowPagingSource: PopularTvshowPagingSource,
        featuredTvshowPagingSource: FeaturedTvshowPagingSource,
        pageConfig: PagingConfig
    ): TvshowRepository =
        TvshowRepositoryImpl(
            tvshowRemoteDataSource,
            popularTvshowPagingSource,
            featuredTvshowPagingSource,
            pageConfig
        )

    @Singleton
    @Provides
    fun providePeopleRepository(
        peopleRemoteDataSource: PeopleRemoteDataSource,
        pageConfig: PagingConfig,
        popularPeoplePagingSource: PopularPeoplePagingSource
    ): PeopleRepository =
        PeopleRepositoryImpl(peopleRemoteDataSource, pageConfig, popularPeoplePagingSource)
}