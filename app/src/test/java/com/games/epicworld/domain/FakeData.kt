package com.games.epicworld.domain

import com.games.epicworld.domain.entity.games.GameResultsEntity
import com.games.epicworld.domain.entity.games.GamesEntity

/**
 * Created by Ruben Quadros on 02/08/21
 **/
object FakeData {
    fun getFakeGames() = GamesEntity(getGamesEntity())

    private fun getGamesEntity(): List<GameResultsEntity> {
        val gameResults: ArrayList<GameResultsEntity> = ArrayList()
        gameResults.add(GameResultsEntity(
            1,
            "Max Payne",
            "",
            4.5,
        ))
        gameResults.add(GameResultsEntity(
            2,
            "GTA V",
            "",
            4.8,
        ))
        return gameResults
    }
}