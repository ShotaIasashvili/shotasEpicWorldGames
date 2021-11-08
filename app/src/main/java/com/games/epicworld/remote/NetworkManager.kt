package com.games.epicworld.remote

import com.games.epicworld.remote.rest.RestApi

/**
 * Created by Ruben Quadros on 01/08/21
 **/
interface NetworkManager {
    fun restApi(): RestApi
}