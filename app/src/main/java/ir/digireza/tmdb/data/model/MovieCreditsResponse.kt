package ir.digireza.tmdb.data.model

import ir.digireza.tmdb.domain.entity.MovieCredits

class MovieCreditsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>
) {

    fun toEntity(): List<MovieCredits> = cast.filter {
        it.profile_path != null && it.profile_path.isNotBlank()
    }.map {
        MovieCredits(
            _posterPath = it.profile_path ?: "",
            name = it.name,
            knownFor = it.known_for_department
        )
    }.toMutableList().apply {
        addAll(crew.filter {
            it.profile_path != null && it.profile_path.isNotBlank()
        }.map {
            MovieCredits(
                _posterPath = it.profile_path ?: "",
                name = it.name,
                knownFor = it.known_for_department
            )
        })
    }


    class Cast(
        val id: Int,
        val known_for_department: String,
        val name: String,
        val original_name: String,
        val popularity: Double,
        val profile_path: String?,
    )

    class Crew(
        val id: Int,
        val known_for_department: String,
        val name: String,
        val original_name: String,
        val popularity: Double,
        val profile_path: String?,
        val department: String,
        val job: String,
    )

}