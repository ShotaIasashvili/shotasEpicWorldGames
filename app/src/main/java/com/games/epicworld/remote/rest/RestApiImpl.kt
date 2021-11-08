package com.games.epicworld.remote.rest

import com.games.epicworld.remote.model.request.GetAllGamesRequest
import com.games.epicworld.remote.model.request.GetGameDetailsRequest
import com.games.epicworld.remote.model.request.GetGameVideosRequest
import com.games.epicworld.remote.model.request.SearchGamesRequest
import com.games.epicworld.remote.model.response.allgames.GetAllGamesResponse
import com.games.epicworld.remote.model.response.gamedetails.GetGameDetailsResponse
import com.games.epicworld.remote.model.response.gamevideos.GetGameVideosResponse
import com.games.epicworld.remote.model.response.search.SearchGamesResponse
import com.games.epicworld.remote.retrofit.RetrofitApi
import javax.inject.Inject

/**
 * Created by Ruben Quadros on 01/08/21
 **/
class RestApiImpl @Inject constructor(private val retrofitApi: RetrofitApi): RestApi {

    override suspend fun getAllGames(getAllGamesRequest: GetAllGamesRequest): GetAllGamesResponse {
        return retrofitApi.getAllGames(getAllGamesRequest.nextPage, getAllGamesRequest.pageSize)
    }

    override suspend fun getGameDetails(getGameDetailsRequest: GetGameDetailsRequest): GetGameDetailsResponse {
        return retrofitApi.getGameDetails(getGameDetailsRequest.gameId)
    }

    override suspend fun getGameVideos(getGameVideosRequest: GetGameVideosRequest): GetGameVideosResponse {
        return retrofitApi.getGameVideos(getGameVideosRequest.gameId)
    }

    override suspend fun searchGames(searchGamesRequest: SearchGamesRequest): SearchGamesResponse {
        return retrofitApi.searchGames(searchGamesRequest.query)
    }

}