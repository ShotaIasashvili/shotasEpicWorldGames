package com.games.epicworld.domain.entity.games


data class GamesEntity(
    val gameEntities: List<GameResultsEntity>
) {
    constructor(): this(arrayListOf())
}
