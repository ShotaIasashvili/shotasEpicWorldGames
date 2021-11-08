package com.games.epicworld.data.repository

import com.games.epicworld.data.DataSource
import com.games.epicworld.data.mapper.ErrorMapper
import com.games.epicworld.data.mapper.GamesMapper
import com.games.epicworld.domain.entity.base.Record
import com.games.epicworld.domain.entity.gamedetails.GameDetailsEntity
import com.games.epicworld.domain.entity.games.GamesEntity
import com.games.epicworld.domain.entity.gamevideos.GameVideosEntity
import com.games.epicworld.domain.repository.GamesRepository
import com.games.epicworld.remote.RemoteException
import com.games.epicworld.remote.model.request.GetAllGamesRequest
import com.games.epicworld.remote.model.request.GetGameDetailsRequest
import com.games.epicworld.remote.model.request.GetGameVideosRequest
import com.games.epicworld.remote.model.request.SearchGamesRequest
import javax.inject.Inject


class GamesRepositoryImpl @Inject constructor(private val dataSource: DataSource): GamesRepository {

    private val gamesMapper = GamesMapper()
    private val errorMapper = ErrorMapper()

    override suspend fun getAllGames(nextPage: Int): Record<GamesEntity> {
        return try {
            dataSource.api().restApi().getAllGames(GetAllGamesRequest(nextPage)).run {
                gamesMapper.mapGamesResponse(this)
            }
        } catch (e: RemoteException) {
            errorMapper.mapErrorRecord(e)
        }
    }

    override suspend fun getGameDetails(gameId: Int): Record<GameDetailsEntity> {
        return try {
            dataSource.api().restApi().getGameDetails(GetGameDetailsRequest(gameId)).run {
                gamesMapper.mapGameDetailsResponse(this)
            }
        } catch (e: RemoteException) {
            errorMapper.mapErrorRecord(e)
        }
    }

    override suspend fun getGameVideos(gameId: Int): Record<GameVideosEntity> {
        return try {
            dataSource.api().restApi().getGameVideos(GetGameVideosRequest((gameId))).run {
                gamesMapper.mapGameVideosResponse(this)
            }
        } catch (e: RemoteException) {
            errorMapper.mapErrorRecord(e)
        }
    }

    override suspend fun searchGames(query: String): Record<GamesEntity> {
        return try {
            dataSource.api().restApi().searchGames(SearchGamesRequest(query)).run {
                gamesMapper.mapSearchGamesResponse(this)
            }
        } catch (e: RemoteException) {
            errorMapper.mapErrorRecord(e)
        }
    }
}