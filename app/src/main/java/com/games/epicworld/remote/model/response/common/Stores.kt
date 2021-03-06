package com.games.epicworld.remote.model.response.common

import com.google.gson.annotations.SerializedName
import com.games.epicworld.domain.entity.gamedetails.StoresEntity

/**
 * Created by Ruben Quadros on 01/08/21
 **/
data class Stores(
    @SerializedName("id")
    val id : Int,
    @SerializedName("store")
    val store : Store
)

fun Stores.toEntity() = StoresEntity(id, store.toEntity())

fun List<Stores>.toEntity() = map { it.toEntity() }