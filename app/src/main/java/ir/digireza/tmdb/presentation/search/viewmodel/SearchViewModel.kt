package ir.digireza.tmdb.presentation.search.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.digireza.tmdb.domain.entity.Movie
import ir.digireza.tmdb.domain.entity.People
import ir.digireza.tmdb.domain.entity.Tvshow
import ir.digireza.tmdb.domain.usecase.movie.SearchMovie
import ir.digireza.tmdb.domain.usecase.people.SearchPeople
import ir.digireza.tmdb.domain.usecase.tvshow.SearchTvshow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchPeople: SearchPeople,
    private val searchMovie: SearchMovie,
    private val searchTvshow: SearchTvshow,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val debounceTime: Long = 800

    val tabItems: List<String> = listOf("Movie", "Tv Show", "People")
    var selectedItemIndex by mutableStateOf(0)
    var searchText: String by mutableStateOf("")


    var isLoading by mutableStateOf(false)
        private set


    fun onTabSelected(index: Int) {
        selectedItemIndex = index
        resetData()
    }

    private fun resetData() {
        searchText = ""
    }

    fun searchMovie(): Flow<PagingData<Movie>> = searchMovie.invoke(searchText)
        .flowOn(dispatcher)
        .cachedIn(viewModelScope)
        .debounce(debounceTime)

    fun searchTvshow(): Flow<PagingData<Tvshow>> = searchTvshow.invoke(searchText)
        .flowOn(dispatcher)
        .cachedIn(viewModelScope)
        .debounce(debounceTime)

    fun searchPeople(): Flow<PagingData<People>> = searchPeople.invoke(searchText)
        .flowOn(dispatcher)
        .cachedIn(viewModelScope)
        .debounce(debounceTime)

}