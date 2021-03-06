package com.games.epicworld.viewmodel

import androidx.paging.PagingSource
import com.games.epicworld.domain.interactor.GamesSource
import com.games.epicworld.presentation.base.ScreenState
import com.games.epicworld.presentation.home.HomeViewModel
import com.games.epicworld.presentation.home.ui.HomeState
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.orbitmvi.orbit.test

/**
 * Created by Ruben Quadros on 02/08/21
 **/
@ExperimentalCoroutinesApi
class HomeVMTest {

    private val mockGamesSource = mockk<GamesSource>()
    private val initialState = HomeState(ScreenState.Loading, null, null)

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
        Dispatchers.setMain(Dispatchers.Unconfined)
        every { runBlocking { mockGamesSource.load(any()) } } returns PagingSource.LoadResult.Page(
            data = arrayListOf(),
            nextKey = 2,
            prevKey = null
        )
    }

    @Test
    fun `vm should fetch all games on initiation`() = runBlocking {
        val homeViewModel = HomeViewModel(mockGamesSource).test(initialState = initialState).apply {
            runOnCreate()
        }
        homeViewModel.stateObserver.awaitCount(2)
        Assert.assertTrue(homeViewModel.stateObserver.values.last().error == null)
        Assert.assertTrue(homeViewModel.stateObserver.values.last().screenState == ScreenState.Success)
        Assert.assertTrue(homeViewModel.stateObserver.values.last().games != null)
        Assert.assertTrue(homeViewModel.sideEffectObserver.values.isEmpty())
    }

    @Test
    fun `vm should propagate error state in case of paging error`() = runBlocking {
        val homeViewModel = HomeViewModel(gamesSource = mockGamesSource).test(initialState = initialState)
        homeViewModel.testIntent {
            handlePaginationDataError()
        }
        homeViewModel.stateObserver.awaitCount(2)
        Assert.assertTrue(homeViewModel.stateObserver.values.last().error != null)
        Assert.assertTrue(homeViewModel.stateObserver.values.last().screenState == ScreenState.Error)
        Assert.assertTrue(homeViewModel.stateObserver.values.last().games == null)
        Assert.assertTrue(homeViewModel.sideEffectObserver.values.isEmpty())
    }

    @Test
    fun `vm should post side effect when there is error in appending paging data`() = runBlocking {
        val homeViewModel = HomeViewModel(gamesSource = mockGamesSource).test(initialState = initialState)
        homeViewModel.testIntent {
            handlePaginationAppendError("Error", "OK")
        }
        homeViewModel.sideEffectObserver.awaitCount(1)
        Assert.assertTrue(homeViewModel.sideEffectObserver.values.isNotEmpty())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}