package com.games.epicworld.remote.rest

import com.games.epicworld.remote.model.request.GetAllGamesRequest
import com.games.epicworld.remote.model.request.GetGameDetailsRequest
import com.games.epicworld.remote.model.request.GetGameVideosRequest
import com.games.epicworld.remote.model.request.SearchGamesRequest
import com.games.epicworld.remote.model.response.allgames.GetAllGamesResponse
import com.games.epicworld.remote.model.response.gamedetails.GetGameDetailsResponse
import com.games.epicworld.remote.model.response.gamevideos.GetGameVideosResponse
import com.games.epicworld.remote.model.response.search.SearchGamesResponse

/**
 * Created by Ruben Quadros on 01/08/21
 **/
interface RestApi {
    suspend fun getAllGames(getAllGamesRequest: GetAllGamesRequest): GetAllGamesResponse
    suspend fun getGameDetails(getGameDetailsRequest: GetGameDetailsRequest): GetGameDetailsResponse
    suspend fun getGameVideos(getGameVideosRequest: GetGameVideosRequest): GetGameVideosResponse
    suspend fun searchGames(searchGamesRequest: SearchGamesRequest): SearchGamesResponse
}