package ir.digireza.tmdb.domain.entity

import ir.digireza.tmdb.app.constants.Constant

data class MoviePictures(
    private val _posterPath: String,
) {
    val posterPath:String get()= "${Constant.IMAGE_ADDRESS}$_posterPath"
}