package ir.digireza.tmdb.data.datasources.remote.client

import ir.digireza.tmdb.app.constants.Constant
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface TMDBServices {
    @GET
    suspend fun getRequest(@Url url: String , @QueryMap queryMap: Map<String , String>): ResponseBody
}