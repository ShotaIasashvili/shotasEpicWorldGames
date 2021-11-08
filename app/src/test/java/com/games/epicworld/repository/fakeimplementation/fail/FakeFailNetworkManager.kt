package com.games.epicworld.repository.fakeimplementation.fail

import com.games.epicworld.remote.NetworkManager
import com.games.epicworld.remote.rest.RestApi

/**
 * Created by Ruben Quadros on 01/08/21
 **/
class FakeFailNetworkManager: NetworkManager {
    override fun restApi(): RestApi = FakeFailApi()
}