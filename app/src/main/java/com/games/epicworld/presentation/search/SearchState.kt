package com.games.epicworld.presentation.search

import com.games.epicworld.domain.entity.games.GameResultsEntity


sealed class SearchState {
    object InitialState: SearchState()
    object LoadingState: SearchState()
    data class SearchResultState(val searchResults: List<GameResultsEntity>): SearchState()
    object NoResultsState: SearchState()
    object ErrorState: SearchState()
}