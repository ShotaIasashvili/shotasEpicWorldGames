package com.games.epicworld.viewmodel

import com.games.epicworld.CoroutinesTestRule
import com.games.epicworld.domain.entity.base.Record
import com.games.epicworld.domain.entity.games.GamesEntity
import com.games.epicworld.domain.interactor.GameSearchUseCase
import com.games.epicworld.domain.repository.GamesRepository
import com.games.epicworld.presentation.search.GameSearchViewModel
import com.games.epicworld.presentation.search.SearchState
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.orbitmvi.orbit.test


@ExperimentalCoroutinesApi
class GameSearchVMTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val mockRepository = mockk<GamesRepository>()
    private val useCase = mockk<GameSearchUseCase>()
    private val initialState = SearchState.InitialState

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun `vm should be able to manager view state`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val gameSearchViewModel = GameSearchViewModel(
            useCase,
            coroutinesTestRule.testDispatcher
        ).test(initialState = initialState)
        every { runBlocking { mockRepository.searchGames("gta") } } answers  {
            Record(GamesEntity(), null)
        }
        gameSearchViewModel.testIntent {
            searchGame("gta")
        }
        gameSearchViewModel.stateObserver.awaitCount(3)
        Assert.assertTrue(gameSearchViewModel.stateObserver.values.contains(SearchState.InitialState))
        Assert.assertTrue(gameSearchViewModel.sideEffectObserver.values.isEmpty())
    }

    @Test
    fun `vm should invoke use case to get search results`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        every { runBlocking { mockRepository.searchGames("gta") } } answers  {
            Record(GamesEntity(), null)
        }
        every { runBlocking { useCase.invoke(any(), any(), any(), any()) } } answers { Record(GamesEntity(), null) }
        val gameSearchViewModel = GameSearchViewModel(
            useCase,
            coroutinesTestRule.testDispatcher
        )
        gameSearchViewModel.searchGame("abc")
        gameSearchViewModel.test(initialState = initialState).apply {
            sideEffectObserver.values.isEmpty()
            stateObserver.awaitCount(3)
            stateObserver.values.contains(SearchState.LoadingState)
            stateObserver.values.contains(SearchState.SearchResultState(arrayListOf()))
        }
    }

    @Test
    fun `vm should post side effect to navigate to details`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val gameSearchViewModel = GameSearchViewModel(
            useCase,
            coroutinesTestRule.testDispatcher
        ).test(initialState = initialState)

        gameSearchViewModel.testIntent {
            handleDetailsNavigation(1)
        }
        gameSearchViewModel.sideEffectObserver.awaitCount(1)
        Assert.assertTrue(gameSearchViewModel.sideEffectObserver.values.isNotEmpty())
    }
}