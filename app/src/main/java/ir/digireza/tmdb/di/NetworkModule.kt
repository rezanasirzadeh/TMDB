package ir.digireza.tmdb.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.digireza.tmdb.data.datasources.remote.client.RestClient
import ir.digireza.tmdb.data.datasources.remote.client.RetrofitBase
import ir.digireza.tmdb.data.datasources.remote.client.TMDBServices
import ir.digireza.tmdb.data.datasources.remote.parser.JsonParser
import ir.digireza.tmdb.data.datasources.remote.parser.MoshiParser
import ir.digireza.tmdb.data.model.MovieResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideMoshiAdapter(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideBaseRetrofit(moshi: Moshi): RestClient = RetrofitBase(moshi = moshi)

    @Singleton
    @Provides
    fun provideJsonParser(moshi: Moshi): JsonParser = MoshiParser(moshi = moshi)

    @Singleton
    @Provides
    fun provideCoroutinesDispatcher(): CoroutineDispatcher = Dispatchers.IO
}