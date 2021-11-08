package com.games.epicworld.domain.entity.gamedetails


data class PlatformsEntity(
    val platform : PlatformEntity,
    val releasedAt : String?,
    val requirementsEn : RequirementsEntity?,
    val requirementsRu : RequirementsEntity?
)
