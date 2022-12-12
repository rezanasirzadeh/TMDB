package ir.digireza.tmdb.data.datasources.remote.parser

import com.squareup.moshi.Moshi
import java.lang.reflect.Type



//typealias ClassType =

interface JsonParser {

    fun <T> toJson(obj: T, type: Any): String

    fun <T> fromJson(json: String, type:  Type): T
}




// Too bad that we can not use Inline Function within interfaces! :((
inline fun <reified T> Moshi.jsonParser(json: String): T =
    adapter<T>(T::class.java).fromJson(json)!!