package ir.digireza.tmdb.data.model

import com.squareup.moshi.Json
import ir.digireza.tmdb.domain.entity.Genre

class GenreResponse(
    val genres: List<Result>,
) {
    fun toEntity(): List<Genre> = genres.map {
        Genre(
            it.id,
            it.name,
        )
    }


    class Result(
        val id: Int,
        val name: String,
    )
}

