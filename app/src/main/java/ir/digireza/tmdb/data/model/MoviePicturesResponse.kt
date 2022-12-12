package ir.digireza.tmdb.data.model

import com.squareup.moshi.Json
import ir.digireza.tmdb.domain.entity.MoviePictures

class MoviePicturesResponse(
    val backdrops: List<Backdrop>,
    val id: Int,
    val logos: List<Logo>,
    val posters: List<Poster>
) {

    fun toEntity(): List<MoviePictures> = posters.map {
        MoviePictures(_posterPath = it.file_path)
    }

    class Backdrop(
        val aspect_ratio: Double,
        val file_path: String,
        val height: Int,
        val vote_average: Double,
        val vote_count: Int,
        val width: Int
    )

    class Poster(
        val aspect_ratio: Double,
        val file_path: String,
        val height: Int,
        val vote_average: Double,
        val vote_count: Int,
        val width: Int
    )

    class Logo(
        val aspect_ratio: Double,
        val file_path: String,
        val height: Int,
        val vote_average: Double,
        val vote_count: Int,
        val width: Int
    )
}