package com.games.epicworld.injection

import com.games.epicworld.data.repository.GamesRepositoryImpl
import com.games.epicworld.domain.repository.GamesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun providesGamesRepository(gamesRepositoryImpl: GamesRepositoryImpl): GamesRepository
}