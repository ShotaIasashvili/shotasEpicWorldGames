package com.games.epicworld.domain.interactor

import com.games.epicworld.domain.BaseUseCase
import com.games.epicworld.domain.entity.base.Record
import com.games.epicworld.domain.entity.games.GamesEntity
import com.games.epicworld.domain.repository.GamesRepository
import javax.inject.Inject


class GameSearchUseCase @Inject constructor(private val gamesRepository: GamesRepository) :
    BaseUseCase<GameSearchUseCase.RequestValue, Record<GamesEntity>>() {

    override suspend fun run(request: RequestValue): Record<GamesEntity> {
        return gamesRepository.searchGames(request.searchQuery)
    }

    data class RequestValue(val searchQuery: String)
}