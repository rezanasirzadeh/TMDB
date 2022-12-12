package ir.digireza.tmdb.presentation.tvshows.components

import androidx.annotation.StringRes
import ir.digireza.tmdb.domain.entity.Movie
import ir.digireza.tmdb.domain.entity.Tvshow


data class AllTvshowsState(
    val isLoading: Boolean = false,
    val tvshows: List<Tvshow> = emptyList(),
    val errorMessage: String = ""
)