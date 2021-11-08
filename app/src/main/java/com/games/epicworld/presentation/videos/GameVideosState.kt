package com.games.epicworld.presentation.videos

import com.games.epicworld.domain.entity.base.ErrorRecord
import com.games.epicworld.domain.entity.gamevideos.GameVideosEntity
import com.games.epicworld.presentation.base.ScreenState

/**
 * Created by Ruben Quadros on 09/08/21
 **/
data class GameVideosState(
    val screenState: ScreenState,
    val gameVideos: GameVideosEntity?,
    val error: ErrorRecord?
)
