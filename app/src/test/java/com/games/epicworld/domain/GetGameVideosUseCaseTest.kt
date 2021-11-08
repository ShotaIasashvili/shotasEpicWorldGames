package com.games.epicworld.domain

import com.games.epicworld.CoroutinesTestRule
import com.games.epicworld.domain.entity.base.Record
import com.games.epicworld.domain.entity.gamevideos.GameVideosEntity
import com.games.epicworld.domain.interactor.GetGameVideosUseCase
import com.games.epicworld.domain.repository.GamesRepository
import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Ruben Quadros on 11/08/21
 **/
@ExperimentalCoroutinesApi
class GetGameVideosUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val mockGamesRepository = mockk<GamesRepository>()
    private val getGameVideosUseCase = GetGameVideosUseCase(mockGamesRepository)

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun `should get game videos from repository`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        every { runBlocking { mockGamesRepository.getGameVideos(1) } } answers {
            Record(GameVideosEntity(), null)
        }
        getGameVideosUseCase.invoke(
            coroutinesTestRule.testCoroutineScope,
            coroutinesTestRule.testDispatcher,
            GetGameVideosUseCase.RequestValue(1)
        ) {
            verify { runBlocking { mockGamesRepository.getGameVideos(1) } }
            confirmVerified(mockGamesRepository)
            Assert.assertTrue(it?.data != null)
            Assert.assertTrue(it?.error == null)
        }
    }
}