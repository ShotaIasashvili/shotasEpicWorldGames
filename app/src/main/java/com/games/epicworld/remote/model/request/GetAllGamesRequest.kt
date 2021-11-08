package com.games.epicworld.remote.model.request

import com.games.epicworld.remote.ApiConstants

/**
 * Created by Ruben Quadros on 01/08/21
 **/
data class GetAllGamesRequest(
    val nextPage: Int,
    val pageSize: Int = ApiConstants.PAGE_SIZE
)
