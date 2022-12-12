package ir.digireza.tmdb.domain.entity

import ir.digireza.tmdb.app.constants.Constant
import ir.digireza.tmdb.data.model.GenreResponse

data class MovieDetails(
    private val _posterPath: String,
    val voteAvg: Double,
    val voteCount: Int,
    val status: String,
    val originalTitle: String,
    val releaseDate: String,
    val productionCounties: List<String>,
    val runtime: Int,
    val genres: List<String>,
    val spokenLanguages: List<String>,
    val overview: String,
    val productionCompanies: List<ProductionCompany>
) {
    val posterPath: String get()= "${Constant.IMAGE_ADDRESS}$_posterPath"
}



data class ProductionCompany(val name: String, private val _logoPath: String) {
    val logoPath: String get() = "${Constant.IMAGE_ADDRESS}$_logoPath"
}