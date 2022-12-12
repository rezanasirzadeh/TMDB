package ir.digireza.tmdb.data.model

import ir.digireza.tmdb.domain.entity.Genre
import ir.digireza.tmdb.domain.entity.MovieDetails
import ir.digireza.tmdb.domain.entity.ProductionCompany
import javax.annotation.Nullable


class MovieDetailsResponse(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection?,
    val budget: Int,
    val genres: List<Genres>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {

    fun toEntity(): MovieDetails = MovieDetails(
        _posterPath = poster_path,
        voteAvg = vote_average,
        voteCount = vote_count,
        status = status,
        originalTitle = original_title,
        releaseDate = release_date,
        productionCompanies = production_companies.filter { it.logo_path != null && it.logo_path.isNotBlank() }.map {
            ProductionCompany(
                name = it.name,
                _logoPath = it.logo_path ?:""
            )
        },
        productionCounties = production_countries.map { it.name },
        genres = genres.map { it.name },
        runtime = runtime,
        spokenLanguages = spoken_languages.map { it.english_name },
        overview = overview
    )

    class BelongsToCollection(
        val backdrop_path: String?,
        val id: Int,
        val name: String,
        val poster_path: String?
    )

    class ProductionCompany(
        val id: Int,
        val logo_path: String?,
        val name: String,
        val origin_country: String
    )

    class ProductionCountry(
        val name: String
    )

    class SpokenLanguage(
        val english_name: String,
        val name: String
    )


    class Genres(
        val id: Int,
        val name: String,
    )
}



