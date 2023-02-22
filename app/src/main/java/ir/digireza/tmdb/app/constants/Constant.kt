package ir.digireza.tmdb.app.constants

class Constant {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val TMDB_TOKEN = "d4b0720551ae06ceed2df4055ad0d09a"

        const val IMAGE_ADDRESS  = "https://image.tmdb.org/t/p/w500/"

        // Intent params
        const val PAGE_TITLE_KEY = "page_title_key"
        const val PAGE_DATA_TYPE = "page_data_type"

        const val CLICKED_ITEM_ID = "clicked_item_id"
    }
}

enum class MoviesType{ populraMovies, featuredMovies, upcomingMovies}
enum class TvshowType{ populraTvshows, featuredTvshows}