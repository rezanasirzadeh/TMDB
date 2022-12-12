package ir.digireza.tmdb.domain.repository

import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.domain.entity.Genre

interface GenreRepository {
    suspend fun getMovieGenres(): DataState<List<Genre>>

    suspend fun getTvshowGenres(): DataState<List<Genre>>
}