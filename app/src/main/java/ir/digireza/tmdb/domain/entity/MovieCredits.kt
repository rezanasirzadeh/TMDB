package ir.digireza.tmdb.domain.entity

import ir.digireza.tmdb.app.constants.Constant

data class MovieCredits(
    private val _posterPath: String,
    val name: String,
    val knownFor:String
) {
    val posterPath: String get() = "${Constant.IMAGE_ADDRESS}$_posterPath"
}