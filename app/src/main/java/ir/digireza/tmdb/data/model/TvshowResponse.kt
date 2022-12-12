package ir.digireza.tmdb.data.model

import com.squareup.moshi.Json
import ir.digireza.tmdb.domain.entity.Tvshow

class TvshowResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int


) {
    fun toEntity(): List<Tvshow> = results.map {
        Tvshow(
            it.id,
            it.name,
            it.overview,
            it.popularity,
            it.poster_path,
            it.first_air_date,
            it.vote_average,
            total_pages
        )
    }


    class Result(
        val first_air_date: String,
        val genre_ids: List<Int>,
        val id: Int,
        val name: String,
        val origin_country: List<String>,
        val original_language: String,
        val original_name: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String,
        val vote_average: Double,
        val vote_count: Int,
    )
}

