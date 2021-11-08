package com.games.epicworld.presentation.search

   
sealed class SearchSideEffect {
    data class NavigateToDetails(val id: Int): SearchSideEffect()
}