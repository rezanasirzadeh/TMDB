package ir.digireza.tmdb.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class BasePagingSource<V : Any> : PagingSource<Int, V>() {

    override fun getRefreshKey(state: PagingState<Int, V>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


    fun getNextKey(position: Int , data: List<Any>): Int? = if (data.isNotEmpty()) position + 1 else null

    fun getPrevKey(position: Int , ): Int? = if (position == 1) null else position

}