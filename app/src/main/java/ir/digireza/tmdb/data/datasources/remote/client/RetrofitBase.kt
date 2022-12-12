package ir.digireza.tmdb.data.datasources.remote.client

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ir.digireza.tmdb.app.constants.Constant
import ir.digireza.tmdb.app.base.error.ServerException
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class RetrofitBase @Inject constructor(private val moshi: Moshi) : RestClient{

    private val service: TMDBServices by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
            .create(TMDBServices::class.java)
    }




    override suspend fun getRequest(url: String, data: MutableMap<String, String>): ResponseBody {

        try {
            data["api_key"] = Constant.TMDB_TOKEN
            return service.getRequest(url = url, queryMap = data)

        } catch (error: Exception) {
            // todo: Return separate exception classes according to each exception type(e.g SocketException, HTTPException)
            throw ServerException(error.message ?: "Server error!")
        }
    }
}