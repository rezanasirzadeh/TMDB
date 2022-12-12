package ir.digireza.tmdb.data.datasources.remote.parameters

class SearchParam(
    private val page:Int,
    private val query: String
) {

    fun toJson(): MutableMap<String , String> = mutableMapOf(
        "page" to page.toString(),
        "query" to query
    )
}