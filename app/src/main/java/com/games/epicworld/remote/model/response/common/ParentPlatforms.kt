package com.games.epicworld.remote.model.response.common

import com.google.gson.annotations.SerializedName
import com.games.epicworld.domain.entity.gamedetails.ParentPlatformsEntity

/**
 * Created by Ruben Quadros on 01/08/21
 **/
data class ParentPlatforms(
    @SerializedName("platform")
    val platform : Platform
)

fun ParentPlatforms.toEntity() = ParentPlatformsEntity(platform.toEntity())

fun List<ParentPlatforms>.toEntity() = map { it.toEntity() }