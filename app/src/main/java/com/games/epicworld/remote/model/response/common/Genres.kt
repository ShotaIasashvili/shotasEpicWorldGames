package com.games.epicworld.remote.model.response.common

import com.google.gson.annotations.SerializedName
import com.games.epicworld.domain.entity.gamedetails.GenresEntity

/**
 * Created by Ruben Quadros on 01/08/21
 **/
data class Genres(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("slug")
    val slug : String,
    @SerializedName("games_count")
    val gamesCount : Int,
    @SerializedName("image_background")
    val imageBackground : String
)

fun Genres.toEntity() = GenresEntity(id, name, slug, gamesCount, imageBackground)

fun List<Genres>.toEntity() = map { it.toEntity() }