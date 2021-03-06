package com.games.epicworld.viewmodel

import com.games.epicworld.CoroutinesTestRule
import com.games.epicworld.domain.entity.base.ErrorRecord
import com.games.epicworld.domain.entity.base.Record
import com.games.epicworld.domain.entity.gamedetails.GameDetailsEntity
import com.games.epicworld.domain.interactor.GetGameDetailsUseCase
import com.games.epicworld.domain.repository.GamesRepository
import com.games.epicworld.presentation.base.ScreenState
import com.games.epicworld.presentation.details.GameDetailsViewModel
import com.games.epicworld.presentation.details.GameDetailsState
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

/**
 * Created by Ruben Quadros on 07/08/21
 **/
@ExperimentalCoroutinesApi
class GameDetailsVMTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val mockRepository = mockk<GamesRepository>()
    private val useCase = GetGameDetailsUseCase(mockRepository)
    private val initialState = GameDetailsState(ScreenState.Loading, null, null)

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun `vm should invoke use case to get game details`() = coroutinesTestRule.testCoroutineScope.runBlockingTest {
        val gameDetailsViewModel = GameDetailsViewModel(
            useCase,
            coroutinesTestRule.testDispatcher
        ).test(initialState = initialState)
        every { runBlocking { mockRepository.getGameDetails(2) } } answers {
            Record(GameDetailsEntity(), null)
        }
        gameDetailsViewModel.testIntent {
            getGameDetails(2)
        }
        gameDetailsViewModel.stateObserver.awaitCount(2)
        Assert.assertTrue(gameDetailsViewModel.stateObserver.values.last().screenState == ScreenState.Success)
        Assert.assertTrue(gameDetailsViewModel.stateObserver.values.last().gameDetails != null)
        Assert.assertTrue(gameDetailsViewModel.stateObserver.values.last().error == null)
        Assert.assertTrue(gameDetailsViewModel.sideEffectObserver.values.isEmpty())
    }

    @Test
    fun `vm should post side effect when there is error in getting game details`() = coroutinesTestRule.testCoroutineScope.runBlockingTest {
        val gameDetailsViewModel = GameDetailsViewModel(
            useCase,
            coroutinesTestRule.testDispatcher
        ).test(initialState = initialState)
        every { runBlocking { mockRepository.getGameDetails(2) } } answers {
            Record(null, ErrorRecord.GenericError)
        }
        gameDetailsViewModel.testIntent {
            getGameDetails(2)
        }
        gameDetailsViewModel.testIntent {
            handleGameIdError()
        }
        gameDetailsViewModel.sideEffectObserver.awaitCount(1)
        Assert.assertTrue(gameDetailsViewModel.sideEffectObserver.values.isNotEmpty())
        gameDetailsViewModel.stateObserver.awaitCount(2)
        Assert.assertTrue(gameDetailsViewModel.stateObserver.values.last().screenState == ScreenState.Error)
        Assert.assertTrue(gameDetailsViewModel.stateObserver.values.last().gameDetails == null)
        Assert.assertTrue(gameDetailsViewModel.stateObserver.values.last().error != null)
    }

    @Test
    fun `vm should post side effect when there is error with game id`() = coroutinesTestRule.testCoroutineScope.runBlockingTest {
        val gameDetailsViewModel = GameDetailsViewModel(
            useCase,
            coroutinesTestRule.testDispatcher
        ).test(initialState = initialState)
        gameDetailsViewModel.testIntent {
            handleGameIdError()
        }
        gameDetailsViewModel.sideEffectObserver.awaitCount(1)
        Assert.assertTrue(gameDetailsViewModel.sideEffectObserver.values.isNotEmpty())
    }

}