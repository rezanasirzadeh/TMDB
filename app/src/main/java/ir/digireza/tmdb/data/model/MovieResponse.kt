package ir.digireza.tmdb.data.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import ir.digireza.tmdb.domain.entity.Movie


class MovieResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<Result>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int


) {
    fun toEntity(): List<Movie> = results.map {
        Movie(
            it.id,
            it.originalLanguage,
            it.originalTitle,
            it.overview,
            it.popularity,
            it.posterPath ?: "",
            it.releaseDate,
            it.title,
            it.voteAverage,
            it.voteCount,
            totalPages,
            page
        )
    }
    class Result(
        @Json(name = "adult")
        val adult: Boolean,
        @Json(name = "genre_ids")
        val genreIds: List<Int>,
        @Json(name = "id")
        val id: Int,
        @Json(name = "original_language")
        val originalLanguage: String,
        @Json(name = "original_title")
        val originalTitle: String,
        @Json(name = "overview")
        val overview: String,
        @Json(name = "popularity")
        val popularity: Double,
        @Json(name = "poster_path")
        val posterPath: String?,
        @Json(name = "release_date")
        val releaseDate: String,
        @Json(name = "title")
        val title: String,
        @Json(name = "video")
        val video: Boolean,
        @Json(name = "vote_average")
        val voteAverage: Double,
        @Json(name = "vote_count")
        val voteCount: Int
    )
}


