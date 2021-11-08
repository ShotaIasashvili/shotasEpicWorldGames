package com.games.epicworld.presentation.details

import com.games.epicworld.domain.entity.base.ErrorRecord
import com.games.epicworld.domain.entity.gamedetails.GameDetailsEntity
import com.games.epicworld.presentation.base.ScreenState


data class GameDetailsState(
    val screenState: ScreenState,
    val gameDetails: GameDetailsEntity?,
    val error: ErrorRecord?
)
