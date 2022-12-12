package ir.digireza.tmdb.data.datasources.remote.parameters

class MovieDetailsParam(
    val movieId: Int
) {

    fun toJson(): MutableMap<String, String> = mutableMapOf(
        "movie_id" to movieId.toString()
    )
}
class PersonDetailsParam(
    val personId: Int
) {
}