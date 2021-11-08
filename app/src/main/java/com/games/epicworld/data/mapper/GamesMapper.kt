package com.games.epicworld.data.mapper

import com.games.epicworld.domain.entity.base.Record
import com.games.epicworld.domain.entity.gamedetails.GameDetailsEntity
import com.games.epicworld.domain.entity.games.GamesEntity
import com.games.epicworld.domain.entity.gamevideos.GameVideosEntity
import com.games.epicworld.remote.model.response.allgames.GetAllGamesResponse
import com.games.epicworld.remote.model.response.allgames.toEntity
import com.games.epicworld.remote.model.response.gamedetails.GetGameDetailsResponse
import com.games.epicworld.remote.model.response.gamedetails.toEntity
import com.games.epicworld.remote.model.response.gamevideos.GetGameVideosResponse
import com.games.epicworld.remote.model.response.gamevideos.toEntity
import com.games.epicworld.remote.model.response.search.SearchGamesResponse


class GamesMapper {

    fun mapGamesResponse(allGamesResponse: GetAllGamesResponse): Record<GamesEntity> {
        return Record(GamesEntity(allGamesResponse.results.toEntity()), null)
    }

    fun mapGameDetailsResponse(gameDetailsResponse: GetGameDetailsResponse): Record<GameDetailsEntity> {
        return Record(gameDetailsResponse.toEntity(), null)
    }

    fun mapGameVideosResponse(gameVideosResponse: GetGameVideosResponse): Record<GameVideosEntity> {
        return Record(gameVideosResponse.toEntity(), null)
    }

    fun mapSearchGamesResponse(searchGamesResponse: SearchGamesResponse): Record<GamesEntity> {
        return Record(GamesEntity(searchGamesResponse.results.toEntity()), null)
    }
}