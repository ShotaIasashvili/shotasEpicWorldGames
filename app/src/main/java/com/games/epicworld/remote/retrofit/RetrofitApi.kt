package com.games.epicworld.remote.retrofit

import com.games.epicworld.remote.model.response.allgames.GetAllGamesResponse
import com.games.epicworld.remote.model.response.gamedetails.GetGameDetailsResponse
import com.games.epicworld.remote.model.response.gamevideos.GetGameVideosResponse
import com.games.epicworld.remote.model.response.search.SearchGamesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Ruben Quadros on 01/08/21
 **/
interface RetrofitApi {

    @GET("/api/games")
    suspend fun getAllGames(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
    ): GetAllGamesResponse

    @GET("/api/games/{id}")
    suspend fun getGameDetails(
        @Path("id") gameId: Int
    ): GetGameDetailsResponse

    @GET("api/games/{id}/movies")
    suspend fun getGameVideos(
        @Path("id") gameId: Int
    ): GetGameVideosResponse

    @GET("api/games?parent_platforms=1,2,3&search_precise=false&search_exact=false")
    suspend fun searchGames(
        @Query("search") query: String
    ): SearchGamesResponse
}