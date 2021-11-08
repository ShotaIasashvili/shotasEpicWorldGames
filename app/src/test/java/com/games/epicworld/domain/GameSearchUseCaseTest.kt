package com.games.epicworld.domain

import com.games.epicworld.CoroutinesTestRule
import com.games.epicworld.domain.entity.base.Record
import com.games.epicworld.domain.entity.games.GamesEntity
import com.games.epicworld.domain.interactor.GameSearchUseCase
import com.games.epicworld.domain.repository.GamesRepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class GameSearchUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val mockGamesRepository = mockk<GamesRepository>()
    private val gameSearchUseCase = GameSearchUseCase(mockGamesRepository)

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun `should get search results entity from repository`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        every { runBlocking { mockGamesRepository.searchGames("gta") } } answers {
            Record(GamesEntity(), null)
        }
        gameSearchUseCase.invoke(
            coroutinesTestRule.testCoroutineScope,
            coroutinesTestRule.testDispatcher,
            GameSearchUseCase.RequestValue("gta")
        ) {
            verify { runBlocking { mockGamesRepository.searchGames("gta") } }
            confirmVerified(mockGamesRepository)
            Assert.assertTrue(it?.data != null)
            Assert.assertTrue(it?.error == null)
        }
    }

}