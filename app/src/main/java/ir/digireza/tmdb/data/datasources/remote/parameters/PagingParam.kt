package ir.digireza.tmdb.data.datasources.remote.parameters

class PagingParam(
    private val page: Int = 1
) {
    fun toJson(): MutableMap<String, String> = mutableMapOf(
        "page" to page.toString()
    )
}