package com.games.epicworld.repository.fakeimplementation.fail

import com.games.epicworld.remote.RemoteException
import com.games.epicworld.remote.model.request.GetAllGamesRequest
import com.games.epicworld.remote.model.request.GetGameDetailsRequest
import com.games.epicworld.remote.model.request.GetGameVideosRequest
import com.games.epicworld.remote.model.request.SearchGamesRequest
import com.games.epicworld.remote.model.response.allgames.GetAllGamesResponse
import com.games.epicworld.remote.model.response.gamedetails.GetGameDetailsResponse
import com.games.epicworld.remote.model.response.gamevideos.GetGameVideosResponse
import com.games.epicworld.remote.model.response.search.SearchGamesResponse
import com.games.epicworld.remote.rest.RestApi

/**
 * Created by Ruben Quadros on 01/08/21
 **/
class FakeFailApi: RestApi {
    override suspend fun getAllGames(getAllGamesRequest: GetAllGamesRequest): GetAllGamesResponse {
        throw RemoteException.GenericError("Api Error")
    }

    override suspend fun getGameDetails(getGameDetailsRequest: GetGameDetailsRequest): GetGameDetailsResponse {
        throw RemoteException.ServerError("Server Error")
    }

    override suspend fun getGameVideos(getGameVideosRequest: GetGameVideosRequest): GetGameVideosResponse {
        throw RemoteException.NoNetworkError("No Network Error")
    }

    override suspend fun searchGames(searchGamesRequest: SearchGamesRequest): SearchGamesResponse {
        throw RemoteException.ClientError("Missing parameters")
    }
}