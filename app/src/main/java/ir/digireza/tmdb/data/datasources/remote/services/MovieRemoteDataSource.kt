package ir.digireza.tmdb.data.datasources.remote.services

import ir.digireza.tmdb.app.base.error.ServerException
import ir.digireza.tmdb.data.datasources.remote.client.RestClient
import ir.digireza.tmdb.data.datasources.remote.parameters.MovieDetailsParam
import ir.digireza.tmdb.data.datasources.remote.parameters.PagingParam
import ir.digireza.tmdb.data.datasources.remote.parameters.SearchParam
import ir.digireza.tmdb.data.datasources.remote.parser.JsonParser
import ir.digireza.tmdb.data.model.MovieCreditsResponse
import ir.digireza.tmdb.data.model.MovieDetailsResponse
import ir.digireza.tmdb.data.model.MoviePicturesResponse
import ir.digireza.tmdb.data.model.MovieResponse
import okhttp3.ResponseBody
import javax.inject.Inject


interface MovieRemoteDataSource {

    suspend fun getPopularMovies(pageParam: PagingParam):  MovieResponse
    suspend fun getUpComingMovies(pageParam: PagingParam): MovieResponse
    suspend fun getFeatureMoves(pageParam: PagingParam): MovieResponse

    suspend fun searchMovie(searchParam: SearchParam): MovieResponse

    suspend fun getMovieDetails(movieDetailsParam: MovieDetailsParam): MovieDetailsResponse
    suspend fun getMoviePictures(movieDetailsParam: MovieDetailsParam): MoviePicturesResponse
    suspend fun getMovieCredits(movieDetailsParam: MovieDetailsParam): MovieCreditsResponse
}

class MovieRemoteDataSourceImpl @Inject constructor(
    private val restClient: RestClient,
    private val jsonParser: JsonParser
) : MovieRemoteDataSource {

    // todo: Replace ResponseBody(from Retrofit lib) class with a wrapper class


   override suspend fun getPopularMovies(pageParam: PagingParam): MovieResponse {

        try {
            val res: ResponseBody =  restClient.getRequest(url = "movie/popular", data = pageParam.toJson())

            return jsonParser.fromJson<MovieResponse>(res.string(), MovieResponse::class.java)

        } catch (error: ServerException) {
            throw ServerException(error.errorMessage)
        }
    }

    override suspend fun getUpComingMovies(pageParam: PagingParam): MovieResponse {
        try {
            val res: ResponseBody =
                restClient.getRequest(url = "movie/upcoming", data = pageParam.toJson())

            return jsonParser.fromJson<MovieResponse>(res.string(), MovieResponse::class.java)

        } catch (error: ServerException) {
                  throw ServerException(error.errorMessage)
        }
    }

    override suspend fun getFeatureMoves(pageParam: PagingParam): MovieResponse {
        try {
            val res: ResponseBody =
                restClient.getRequest(url = "movie/top_rated", data = pageParam.toJson())

            return jsonParser.fromJson<MovieResponse>(res.string(), MovieResponse::class.java)

        } catch (error: ServerException) {
                  throw ServerException(error.errorMessage)
        }
    }

    override suspend fun searchMovie(searchParam: SearchParam): MovieResponse {
        try {
            val res: ResponseBody =  restClient.getRequest(url = "search/movie", data = searchParam.toJson())

            return jsonParser.fromJson<MovieResponse>(res.string(), MovieResponse::class.java)

        } catch (error: ServerException) {
                  throw ServerException(error.errorMessage)
        }
    }

    override suspend fun getMovieDetails(movieDetailsParam: MovieDetailsParam): MovieDetailsResponse {
        try {
            val res: ResponseBody =  restClient.getRequest(url = "movie/${movieDetailsParam.movieId}")

            return jsonParser.fromJson<MovieDetailsResponse>(res.string(), MovieDetailsResponse::class.java)

        } catch (error: ServerException) {
            throw ServerException(error.errorMessage)
        }
    }

    override suspend fun getMoviePictures(movieDetailsParam: MovieDetailsParam): MoviePicturesResponse {
        try {
            val res: ResponseBody =  restClient.getRequest(url = "movie/${movieDetailsParam.movieId}/images")

            return jsonParser.fromJson<MoviePicturesResponse>(res.string(), MoviePicturesResponse::class.java)

        } catch (error: ServerException) {
            throw ServerException(error.errorMessage)
        }
    }

    override suspend fun getMovieCredits(movieDetailsParam: MovieDetailsParam): MovieCreditsResponse {
        try {
            val res: ResponseBody =  restClient.getRequest(url = "movie/${movieDetailsParam.movieId}/credits")

            return jsonParser.fromJson<MovieCreditsResponse>(res.string(), MovieCreditsResponse::class.java)

        } catch (error: ServerException) {
            throw ServerException(error.errorMessage)
        }
    }


}
