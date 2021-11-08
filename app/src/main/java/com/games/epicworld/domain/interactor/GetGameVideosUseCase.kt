package com.games.epicworld.domain.interactor

import com.games.epicworld.domain.BaseUseCase
import com.games.epicworld.domain.entity.base.Record
import com.games.epicworld.domain.entity.gamevideos.GameVideosEntity
import com.games.epicworld.domain.repository.GamesRepository
import javax.inject.Inject


class GetGameVideosUseCase @Inject constructor(private val gamesRepository: GamesRepository) :
    BaseUseCase<GetGameVideosUseCase.RequestValue, Record<GameVideosEntity>>() {

    override suspend fun run(request: RequestValue): Record<GameVideosEntity> {
        return gamesRepository.getGameVideos(request.gameId)
    }

    data class RequestValue(
        val gameId: Int
    )
}