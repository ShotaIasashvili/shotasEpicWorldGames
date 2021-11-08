package com.games.epicworld.repository.fakeimplementation.success

import com.games.epicworld.data.DataSource
import com.games.epicworld.remote.NetworkManager

/**
 * Created by Ruben Quadros on 01/08/21
 **/
class FakeSuccessDataSource: DataSource {
    override fun api(): NetworkManager = FakeSuccessNetworkManager()
}