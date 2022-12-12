package ir.digireza.tmdb.domain.entity

import ir.digireza.tmdb.app.constants.Constant


data class People(
    val id: Int,
    val name: String,
    private val _profilePath: String?,
    val popularity: Double,
    val knownFor: String,
    val biography: String?,
    val placeOfBirth: String? = null,
) {
    val profilePath = _profilePath
        get() = "${Constant.IMAGE_ADDRESS}$field"
}