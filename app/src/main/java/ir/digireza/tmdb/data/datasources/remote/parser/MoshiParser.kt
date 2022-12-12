package ir.digireza.tmdb.data.datasources.remote.parser

import com.squareup.moshi.Moshi
import java.lang.reflect.Type
import javax.inject.Inject


class MoshiParser @Inject constructor(private val moshi: Moshi) : JsonParser {
    override fun <T> toJson(obj: T, type: Any): String =
        moshi.adapter<T>(type::class.java).toJson(obj)


    override fun <T> fromJson(json: String, type: Type): T =
        moshi.adapter<T>(type).fromJson(json)!!


}