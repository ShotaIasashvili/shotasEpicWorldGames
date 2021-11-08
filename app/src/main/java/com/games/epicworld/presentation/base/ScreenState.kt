package com.games.epicworld.presentation.base


sealed class ScreenState {
    object Loading: ScreenState()
    object Success: ScreenState()
    object Error: ScreenState()
}
