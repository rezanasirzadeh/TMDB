package ir.digireza.tmdb.domain.entity

import ir.digireza.tmdb.app.constants.Constant

data class Tvshow(
    val id: Int,
    val name: String,
    val overview: String,
    val popularity: Double,
    private val _posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val totalPage: Int,
) {
    val posterPath = _posterPath
        get() = "${Constant.IMAGE_ADDRESS}$field"
}