package ir.digireza.tmdb.presentation.people.details.components

import ir.digireza.tmdb.domain.entity.*

data class PersonDetailsState(
    val isLoading: Boolean = false,
    val people: People? = null ,
    val hasError: Boolean = false
)
data class PersonPicturesState(
    val isLoading: Boolean = false,
    val  peoplePictures: List<String> = emptyList(),
)