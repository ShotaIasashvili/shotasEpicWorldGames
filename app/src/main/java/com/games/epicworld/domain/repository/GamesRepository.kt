package com.games.epicworld.domain.repository

import com.games.epicworld.domain.entity.base.Record
import com.games.epicworld.domain.entity.gamedetails.GameDetailsEntity
import com.games.epicworld.domain.entity.games.GamesEntity
import com.games.epicworld.domain.entity.gamevideos.GameVideosEntity


interface GamesRepository {

    suspend fun getAllGames(nextPage: Int): Record<GamesEntity>
    suspend fun getGameDetails(gameId: Int): Record<GameDetailsEntity>
    suspend fun getGameVideos(gameId: Int): Record<GameVideosEntity>
    suspend fun searchGames(query: String): Record<GamesEntity>
}