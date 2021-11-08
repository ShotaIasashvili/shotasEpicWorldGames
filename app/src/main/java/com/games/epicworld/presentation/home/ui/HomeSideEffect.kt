package com.games.epicworld.presentation.home.ui


sealed class HomeSideEffect {
    data class ShowSnackBar(val message: String, val action: String): HomeSideEffect()
}
