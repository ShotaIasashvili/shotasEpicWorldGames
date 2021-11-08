package com.games.epicworld.domain.entity.gamedetails


data class GameDetailsEntity(
    val id : Int,
    val name: String,
    val description: String,
    val rating: Double,
    val released: String,
    val backgroundImage: String,
    val moviesCount: Int,
    val parentPlatformsEntity: List<ParentPlatformsEntity>,
    val platformsEntity: List<PlatformsEntity>,
    val storesEntity: List<StoresEntity>,
    val developersEntity: List<DevelopersEntity>,
    val genresEntity: List<GenresEntity>,
    val publishersEntity: List<PublishersEntity>
) {
    constructor(): this(0, "", "", 0.0, "", "", 0,  arrayListOf(), arrayListOf(), arrayListOf(), arrayListOf(), arrayListOf(), arrayListOf())
}
