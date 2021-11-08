package com.games.epicworld.presentation.home

import com.games.epicworld.domain.interactor.GamesSource
import com.games.epicworld.domain.repository.GamesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    fun providesGamesSource(gamesRepository: GamesRepository) = GamesSource(gamesRepository = gamesRepository)
}