package com.games.epicworld.domain.interactor

import com.games.epicworld.domain.BaseUseCase
import com.games.epicworld.domain.entity.base.Record
import com.games.epicworld.domain.entity.gamedetails.GameDetailsEntity
import com.games.epicworld.domain.repository.GamesRepository
import javax.inject.Inject


class GetGameDetailsUseCase @Inject constructor(private val gamesRepository: GamesRepository) :
    BaseUseCase<GetGameDetailsUseCase.RequestValue, Record<GameDetailsEntity>>() {

    override suspend fun run(request: RequestValue): Record<GameDetailsEntity> {
        return gamesRepository.getGameDetails(request.gameId)
    }

    data class RequestValue(
        val gameId: Int
    )
}