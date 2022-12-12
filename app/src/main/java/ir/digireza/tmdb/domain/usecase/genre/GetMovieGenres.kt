package ir.digireza.tmdb.domain.usecase.genre

import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.app.base.usecase.NoParam
import ir.digireza.tmdb.app.base.usecase.UseCase
import ir.digireza.tmdb.domain.entity.Genre
import ir.digireza.tmdb.domain.repository.GenreRepository
import javax.inject.Inject

class GetMovieGenres @Inject constructor(private val repository: GenreRepository): UseCase<List<Genre>, NoParam>() {
    override suspend fun invoke(param: NoParam): DataState<List<Genre>>  = repository.getMovieGenres()
}