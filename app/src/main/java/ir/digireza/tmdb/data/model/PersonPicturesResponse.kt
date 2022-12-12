package ir.digireza.tmdb.data.model

import ir.digireza.tmdb.domain.entity.PersonPictures

class PersonPicturesResponse(
    val profiles: List<Profiles>
) {

    fun toEntity(): List<PersonPictures> = profiles.map {
        PersonPictures(it.file_path)
    }

    class Profiles(
        val file_path: String
    )
}