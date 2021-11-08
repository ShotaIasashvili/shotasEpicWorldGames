package com.games.epicworld.domain.entity.gamedetails


data class StoreEntity(
    val id : Int,
    val name : String,
    val slug : String,
    val domain : String,
    val gamesCount : Int,
    val imageBackground : String
)
