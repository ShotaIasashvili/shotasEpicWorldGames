package com.games.epicworld.presentation

import androidx.navigation.NavHostController
import com.games.epicworld.presentation.Destinations.Filters
import com.games.epicworld.presentation.Destinations.GameDetails
import com.games.epicworld.presentation.Destinations.GameVideos
import com.games.epicworld.presentation.Destinations.Home
import com.games.epicworld.presentation.Destinations.Search

/**
 * Created by Ruben Quadros on 05/08/21
 **/
object Destinations {
    const val Home = "home"
    const val GameDetails = "gameDetails"
    const val Search = "search"
    const val Filters = "filters"
    const val GameVideos = "gameVideos"

    object GameDetailsArgs {
        const val GameId = "gameId"
    }

    object GameVideosArgs {
        const val GameIdVideo = "gameId"
    }
}

class Actions(navHostController: NavHostController) {
    val openSearch: () -> Unit = {
        navHostController.navigate(Search)
    }

    val openFilter: () -> Unit = {
        navHostController.navigate(Filters)
    }

    val openGameDetails: (Int) -> Unit = { gameId ->
        navHostController.popBackStack(route = Home, inclusive = false)
        navHostController.navigate("$GameDetails/$gameId")
    }

    val openGameVideos: (Int) -> Unit = { gameId ->
        navHostController.navigate("$GameVideos/$gameId")
    }

    val navigateBack: () -> Unit = {
        navHostController.navigateUp()
    }
}