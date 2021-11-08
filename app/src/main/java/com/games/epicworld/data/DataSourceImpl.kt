package com.games.epicworld.data

import com.games.epicworld.remote.NetworkManager
import com.games.epicworld.remote.NetworkManagerImpl
import com.games.epicworld.remote.rest.RestApi
import javax.inject.Inject


class DataSourceImpl @Inject constructor(private val restApi: RestApi): DataSource {
    override fun api(): NetworkManager = NetworkManagerImpl(restApi = restApi)
}