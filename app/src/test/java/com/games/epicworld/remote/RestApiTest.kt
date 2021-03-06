package com.games.epicworld.remote

import com.games.epicworld.remote.model.request.GetAllGamesRequest
import com.games.epicworld.remote.model.request.GetGameDetailsRequest
import com.games.epicworld.remote.model.request.GetGameVideosRequest
import com.games.epicworld.remote.model.request.SearchGamesRequest
import com.games.epicworld.remote.model.response.allgames.GetAllGamesResponse
import com.games.epicworld.remote.model.response.gamedetails.GetGameDetailsResponse
import com.games.epicworld.remote.model.response.gamevideos.GetGameVideosResponse
import com.games.epicworld.remote.model.response.search.SearchGamesResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.HttpURLConnection

/**
 * Created by Ruben Quadros on 01/08/21
 **/
class RestApiTest: BaseTest() {

    @Test
    fun `should get all games when server gives success response`() = runBlocking {
        val expectedResponse = getExpectedResponse("get_all_games_success_response.json", GetAllGamesResponse::class.java)
        getResponse("get_all_games_success_response.json", HttpURLConnection.HTTP_OK)
        val result = restApi.getAllGames(GetAllGamesRequest(1))
        Assert.assertEquals(expectedResponse.results.size, result.results.size)
    }

    @Test
    fun `should get game details when server gives success response`() = runBlocking {
        val expectedResponse = getExpectedResponse("get_game_details_success_response.json", GetGameDetailsResponse::class.java)
        getResponse("get_game_details_success_response.json", HttpURLConnection.HTTP_OK)
        val result = restApi.getGameDetails(GetGameDetailsRequest(1))
        Assert.assertEquals(expectedResponse.id, result.id)
    }

    @Test
    fun `should get game videos when server gives success response`() = runBlocking {
        val expectedResponse = getExpectedResponse("get_game_videos_success_response.json", GetGameVideosResponse::class.java)
        getResponse("get_game_videos_success_response.json", HttpURLConnection.HTTP_OK)
        val result = restApi.getGameVideos(GetGameVideosRequest(1))
        Assert.assertEquals(expectedResponse.count, result.count)
    }

    @Test
    fun `should get search results when server gives success response`() = runBlocking {
        val expectedResponse = getExpectedResponse("search_game_success_response.json", SearchGamesResponse::class.java)
        getResponse("search_game_success_response.json", HttpURLConnection.HTTP_OK)
        val result = restApi.searchGames(SearchGamesRequest("gta"))
        Assert.assertEquals(expectedResponse.count, result.count)
    }

    @Test
    fun `should throw client exception when server sends 4xx response`() {
        Assert.assertThrows(RemoteException.ClientError::class.java) {
            runBlocking {
                getResponse("get_all_games_success_response.json", HttpURLConnection.HTTP_BAD_REQUEST)
                restApi.getAllGames(GetAllGamesRequest(1))
            }
        }
    }

    @Test
    fun `should throw server exception when server sends 5xx response`() {
        Assert.assertThrows(RemoteException.ServerError::class.java) {
            runBlocking {
                getResponse("get_all_games_success_response.json", HttpURLConnection.HTTP_BAD_GATEWAY)
                restApi.getAllGames(GetAllGamesRequest(1))
            }
        }
    }

    @Test
    fun `should throw no network exception in case of timeout`() {
        Assert.assertThrows(RemoteException.NoNetworkError::class.java) {
            runBlocking {
                getTimeout()
                restApi.getAllGames(GetAllGamesRequest(1))
            }
        }
    }
}