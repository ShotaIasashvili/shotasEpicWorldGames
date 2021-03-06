package com.games.epicworld.viewmodel

import com.games.epicworld.CoroutinesTestRule
import com.games.epicworld.domain.entity.base.ErrorRecord
import com.games.epicworld.domain.entity.base.Record
import com.games.epicworld.domain.entity.gamevideos.GameVideosEntity
import com.games.epicworld.domain.interactor.GetGameVideosUseCase
import com.games.epicworld.domain.repository.GamesRepository
import com.games.epicworld.presentation.base.ScreenState
import com.games.epicworld.presentation.videos.GameVideosViewModel
import com.games.epicworld.presentation.videos.GameVideosState
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
 * Created by Ruben Quadros on 11/08/21
 **/
@ExperimentalCoroutinesApi
class GameVideosVMTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val mockRepository = mockk<GamesRepository>()
    private val useCase = GetGameVideosUseCase(mockRepository)
    private val initialState = GameVideosState(ScreenState.Loading, null, null)

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun `vm should invoke use case to get game videos`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val gameVideosViewModel = GameVideosViewModel(
            useCase,
            coroutinesTestRule.testDispatcher
        ).test(initialState = initialState)
        every { runBlocking { mockRepository.getGameVideos(1) } } answers  {
            Record(GameVideosEntity(), null)
        }
        gameVideosViewModel.testIntent {
            getGameVideos(1)
        }
        gameVideosViewModel.stateObserver.awaitCount(2)
        Assert.assertTrue(gameVideosViewModel.stateObserver.values.last().screenState == ScreenState.Success)
        Assert.assertTrue(gameVideosViewModel.stateObserver.values.last().gameVideos != null)
        Assert.assertTrue(gameVideosViewModel.stateObserver.values.last().error == null)
        Assert.assertTrue(gameVideosViewModel.sideEffectObserver.values.isEmpty())
    }

    @Test
    fun `vm should post side effect when there is error in getting game videos`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val gameVideosViewModel = GameVideosViewModel(
            useCase,
            coroutinesTestRule.testDispatcher
        ).test(initialState = initialState)
        every { runBlocking { mockRepository.getGameVideos(1) } } answers {
            Record(null, ErrorRecord.ServerError)
        }
        gameVideosViewModel.testIntent {
            getGameVideos(1)
        }
        gameVideosViewModel.testIntent {
            handleGameVideoError()
        }
        gameVideosViewModel.sideEffectObserver.awaitCount(1)
        Assert.assertTrue(gameVideosViewModel.sideEffectObserver.values.isNotEmpty())
        gameVideosViewModel.stateObserver.awaitCount(2)
        Assert.assertTrue(gameVideosViewModel.stateObserver.values.last().screenState == ScreenState.Error)
        Assert.assertTrue(gameVideosViewModel.stateObserver.values.last().gameVideos == null)
        Assert.assertTrue(gameVideosViewModel.stateObserver.values.last().error != null)
    }

    @Test
    fun `vm should post side effect when there is error with game id`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val gameVideosViewModel = GameVideosViewModel(
            useCase,
            coroutinesTestRule.testDispatcher
        ).test(initialState = initialState)
        gameVideosViewModel.testIntent {
            handleGameIdError()
        }
        gameVideosViewModel.sideEffectObserver.awaitCount(1)
        Assert.assertTrue(gameVideosViewModel.sideEffectObserver.values.isNotEmpty())
    }

    @Test
    fun `vm should post side effect when there are no videos for the game`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val gameVideosViewModel = GameVideosViewModel(
            useCase,
            coroutinesTestRule.testDispatcher
        ).test(initialState = initialState)
        every { runBlocking { mockRepository.getGameVideos(1) } } answers  {
            Record(GameVideosEntity(), null)
        }
        gameVideosViewModel.testIntent {
            getGameVideos(1)
        }
        gameVideosViewModel.testIntent {
            handleNoGameVideos()
        }
        gameVideosViewModel.sideEffectObserver.awaitCount(1)
        Assert.assertTrue(gameVideosViewModel.sideEffectObserver.values.isNotEmpty())
        gameVideosViewModel.stateObserver.awaitCount(2)
        Assert.assertTrue(gameVideosViewModel.stateObserver.values.last().screenState == ScreenState.Success)
        Assert.assertTrue(gameVideosViewModel.stateObserver.values.last().gameVideos?.results?.isEmpty() == true)
        Assert.assertTrue(gameVideosViewModel.stateObserver.values.last().error == null)
    }
}