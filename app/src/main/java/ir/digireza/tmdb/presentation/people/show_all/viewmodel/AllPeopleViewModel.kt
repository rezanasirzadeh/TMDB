package ir.digireza.tmdb.presentation.people.show_all.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.digireza.tmdb.domain.entity.People
import ir.digireza.tmdb.domain.usecase.people.GetPopularPeople
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@HiltViewModel
class AllPeopleViewModel @Inject constructor(
    private val getPopularPeople: GetPopularPeople,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private var _peoplePager: Flow<PagingData<People>> =
        MutableStateFlow<PagingData<People>>(PagingData.empty())
    val peoplePager: Flow<PagingData<People>> get() = _peoplePager



    fun getBodyData() {
        getPopularPeople()
    }

    private fun getPopularPeople() {
        _peoplePager = getPopularPeople.invoke()
            .flowOn(dispatcher)
            .cachedIn(viewModelScope)
    }



}