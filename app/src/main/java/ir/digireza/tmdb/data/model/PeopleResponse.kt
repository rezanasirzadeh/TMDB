package ir.digireza.tmdb.data.model

import com.squareup.moshi.Json
import ir.digireza.tmdb.domain.entity.People


class PeopleResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<PersonDetailsResponse>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int


) {
    fun toEntity(): List<People> = results.map {
        People(
            it.id,
            it.name,
            it.profile_path,
            it.popularity,
            it.known_for_department ?: "",
            it.biography
        )
    }
}


class PersonDetailsResponse(
    val adult: Boolean,
    val id: Int,
    val known_for_department: String?,
    val biography: String?,
    val name: String,
    val popularity: Double,
    val place_of_birth: String?,
    val profile_path: String?,
) {
    fun toEntity(): People =
        People(
            id,
            name,
            profile_path,
            popularity,
            known_for_department ?: "",
            biography,
            place_of_birth
        )
}
