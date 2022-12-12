package ir.digireza.tmdb.presentation.people.details.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.digireza.tmdb.app.base.datastate.DataState
import ir.digireza.tmdb.domain.usecase.people.GetPersonDetails
import ir.digireza.tmdb.domain.usecase.people.GetPersonPictures
import ir.digireza.tmdb.presentation.people.details.components.PersonDetailsState
import ir.digireza.tmdb.presentation.people.details.components.PersonPicturesState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val getPersonDetails: GetPersonDetails,
    private val getPersonPictures: GetPersonPictures,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _personDetailsState = mutableStateOf(PersonDetailsState())
    val personDetailsState: PersonDetailsState by _personDetailsState

    private val _personPicturesState = mutableStateOf(PersonPicturesState())
    val personPicturesState: PersonPicturesState by _personPicturesState

    fun getPersonDetails(personId: Int) {
        _personDetailsState.value = PersonDetailsState(isLoading = true)
        viewModelScope.launch(dispatcher) {
            val response = getPersonDetails.invoke(personId)
            if (response is DataState.DataSuccessState) _personDetailsState.value =
                PersonDetailsState(people = response.data)
            else _personDetailsState.value = PersonDetailsState(hasError = true)
        }
    }

    fun getPersonPictures(personId: Int) {
        _personPicturesState.value = PersonPicturesState(isLoading = true)
        viewModelScope.launch(dispatcher) {
            val response: List<String> = getPersonPictures.invoke(personId)
            _personPicturesState.value = PersonPicturesState(peoplePictures = response)
        }

    }
}