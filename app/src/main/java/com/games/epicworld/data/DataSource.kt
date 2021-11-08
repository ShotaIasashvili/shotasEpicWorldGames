package com.games.epicworld.data

import com.games.epicworld.remote.NetworkManager


interface DataSource {
    fun api(): NetworkManager
}