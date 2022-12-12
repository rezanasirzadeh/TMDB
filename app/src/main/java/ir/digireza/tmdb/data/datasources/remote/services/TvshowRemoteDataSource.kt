package ir.digireza.tmdb.data.datasources.remote.services

import ir.digireza.tmdb.app.base.error.ServerException
import ir.digireza.tmdb.data.datasources.remote.client.RestClient
import ir.digireza.tmdb.data.datasources.remote.parameters.PagingParam
import ir.digireza.tmdb.data.datasources.remote.parameters.SearchParam
import ir.digireza.tmdb.data.datasources.remote.parser.JsonParser
import ir.digireza.tmdb.data.model.MovieResponse
import ir.digireza.tmdb.data.model.TvshowResponse
import ir.digireza.tmdb.domain.entity.Tvshow
import okhttp3.ResponseBody
import javax.inject.Inject

interface TvshowRemoteDataSource {
    suspend fun getPopularTvshows(pageParam: PagingParam): TvshowResponse
    suspend fun getFeatureTvshows(pageParam: PagingParam): TvshowResponse

    suspend fun searchTvshow(searchParam: SearchParam): TvshowResponse
}

class TvshowRemoteDataSourceImpl @Inject constructor(
    private val restClient: RestClient,
    private val jsonParser: JsonParser
) : TvshowRemoteDataSource {

    override suspend fun getPopularTvshows(pageParam: PagingParam): TvshowResponse {
        try {
            val response: ResponseBody = restClient.getRequest("tv/popular", pageParam.toJson())
            return   jsonParser.fromJson<TvshowResponse>(response.string() , TvshowResponse::class.java)
        } catch (error: ServerException) {
                  throw ServerException(error.errorMessage)
        }
    }

    override suspend fun getFeatureTvshows(pageParam: PagingParam): TvshowResponse {
        try {
            val response: ResponseBody = restClient.getRequest("tv/top_rated", pageParam.toJson())
            return   jsonParser.fromJson<TvshowResponse>(response.string() , TvshowResponse::class.java)
        } catch (error: ServerException) {
                  throw ServerException(error.errorMessage)
        }
    }

    override suspend fun searchTvshow(searchParam: SearchParam): TvshowResponse {
        try {
            val response: ResponseBody = restClient.getRequest("search/tv", searchParam.toJson())
            return   jsonParser.fromJson<TvshowResponse>(response.string() , TvshowResponse::class.java)
        } catch (error: ServerException) {
                  throw ServerException(error.errorMessage)
        }
    }


}