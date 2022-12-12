package ir.digireza.tmdb.data.datasources.remote.client

import okhttp3.ResponseBody

interface RestClient {

    suspend fun getRequest(
        url: String,
        data: MutableMap<String, String> = mutableMapOf()
    ): ResponseBody

}