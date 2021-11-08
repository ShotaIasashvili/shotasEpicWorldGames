package com.games.epicworld.presentation.details


sealed class GameDetailsSideEffect {
    object ShowGameIdErrorToast: GameDetailsSideEffect()
    object ShowGameDetailsErrorToast: GameDetailsSideEffect()
}
