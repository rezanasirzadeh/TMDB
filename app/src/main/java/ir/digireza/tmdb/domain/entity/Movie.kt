package ir.digireza.tmdb.domain.entity

import ir.digireza.tmdb.app.constants.Constant

data class Movie(
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    private val _posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val totalPage: Int,
    val currentPage: Int
) {
    val posterPath = _posterPath
        get() = "${Constant.IMAGE_ADDRESS}$field"
}