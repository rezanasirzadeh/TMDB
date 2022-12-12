package ir.digireza.tmdb.domain.entity

import ir.digireza.tmdb.app.constants.Constant

data class PersonPictures(
    private  val _picturePath: String
) {
    val picturePath get() = "${Constant.IMAGE_ADDRESS}$_picturePath"
}