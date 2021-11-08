package com.games.epicworld.presentation.home.ui

import androidx.paging.PagingData
import com.games.epicworld.domain.entity.base.ErrorRecord
import com.games.epicworld.domain.entity.games.GameResultsEntity
import com.games.epicworld.presentation.base.ScreenState
import kotlinx.coroutines.flow.Flow


data class HomeState(
    val screenState: ScreenState,
    val games: Flow<PagingData<GameResultsEntity>>?,
    val error: ErrorRecord?
)
