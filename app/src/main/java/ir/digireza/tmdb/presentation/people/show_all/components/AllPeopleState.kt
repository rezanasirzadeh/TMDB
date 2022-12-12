package ir.digireza.tmdb.presentation.people.show_all.components

import ir.digireza.tmdb.domain.entity.People


data class AllPeopleState(
    val isLoading: Boolean = false,
    val people: List<People> = emptyList(),
    val errorMessage: String = ""
)