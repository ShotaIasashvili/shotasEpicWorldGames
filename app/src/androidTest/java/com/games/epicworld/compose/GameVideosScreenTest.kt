package com.games.epicworld.compose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.games.epicworld.domain.entity.base.ErrorRecord
import com.games.epicworld.domain.entity.gamevideos.GameVideosEntity
import com.games.epicworld.presentation.base.ScreenState
import com.games.epicworld.presentation.videos.GameVideosViewModel
import com.games.epicworld.presentation.videos.GameVideosScreen
import com.games.epicworld.presentation.videos.GameVideosSideEffect
import com.games.epicworld.presentation.videos.GameVideosState
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Ruben Quadros on 13/08/21
 **/
@ExperimentalAnimationApi
class GameVideosScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val gameVideosViewModel = mockk<GameVideosViewModel>()

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
        every { gameVideosViewModel.initData() } answers { }
        every { gameVideosViewModel.createInitialState() } answers {
            GameVideosState(ScreenState.Loading, null, null)
        }
        every { gameVideosViewModel.uiSideEffect() } answers { flow { } }
        every { gameVideosViewModel.getGameVideos(any()) } answers { }
        every { gameVideosViewModel.handleGameIdError() } answers { }
        every { gameVideosViewModel.handleNoGameVideos() } answers { }
        every { gameVideosViewModel.handleGameVideoError() } answers { }
    }

    @Test
    fun loader_should_be_shown_when_fetching_game_videos() {
        every { gameVideosViewModel.uiState() } answers { MutableStateFlow(gameVideosViewModel.createInitialState()) }
        composeTestRule.setContent {
            GameVideosScreen(gameId = 123, navigateBack = { }, gameVideosViewModel = gameVideosViewModel)
        }
        composeTestRule.onNodeWithTag("ProgressBar").assertIsDisplayed()
    }

    @Test
    fun providing_invalid_game_id_should_show_toast_and_exit_screen() {
        every { gameVideosViewModel.uiState() } answers { MutableStateFlow(gameVideosViewModel.createInitialState()) }
        every { gameVideosViewModel.uiSideEffect() } answers {
            flow { emit(GameVideosSideEffect.ShowGameIdErrorToast) }
        }
        var navigateBack = false
        composeTestRule.setContent {
            GameVideosScreen(gameId = 0, navigateBack = { navigateBack = true }, gameVideosViewModel = gameVideosViewModel)
        }
        Assert.assertTrue(navigateBack)
    }

    @Test
    fun should_start_playing_trailer_after_getting_game_videos() {
        every { gameVideosViewModel.uiState() } answers {
            MutableStateFlow(GameVideosState(ScreenState.Success, FakeGamesData.getFakeGameVideos(), null))
        }
        composeTestRule.setContent {
            GameVideosScreen(gameId = 1, navigateBack = { }, gameVideosViewModel = gameVideosViewModel)
        }
        composeTestRule.onNodeWithTag("VideoPlayer").assertIsDisplayed()
    }

    @Test
    fun should_display_current_playing_game_video_title() {
        every { gameVideosViewModel.uiState() } answers {
            MutableStateFlow(GameVideosState(ScreenState.Success, FakeGamesData.getFakeGameVideos(), null))
        }
        composeTestRule.setContent {
            GameVideosScreen(gameId = 1, navigateBack = { }, gameVideosViewModel = gameVideosViewModel)
        }
        composeTestRule.onAllNodesWithText("GTA Online: Smuggler's Run Trailer").assertCountEquals(2)
    }

    @Test
    fun should_show_all_videos_as_a_playlist() {
        every { gameVideosViewModel.uiState() } answers {
            MutableStateFlow(GameVideosState(ScreenState.Success, FakeGamesData.getFakeGameVideos(), null))
        }
        composeTestRule.setContent {
            GameVideosScreen(gameId = 1, navigateBack = { }, gameVideosViewModel = gameVideosViewModel)
        }
        composeTestRule.onAllNodesWithContentDescription("Game Trailer").assertCountEquals(3)
        composeTestRule.onNodeWithContentDescription("Play Game Trailer").assertIsDisplayed()
        composeTestRule.onNodeWithText("Now Playing").assertIsDisplayed()
    }

    @Test
    fun game_play_list_should_be_scrollable() {
        every { gameVideosViewModel.uiState() } answers {
            MutableStateFlow(GameVideosState(ScreenState.Success, FakeGamesData.getFakeGameVideos(), null))
        }
        composeTestRule.setContent {
            GameVideosScreen(gameId = 1, navigateBack = { }, gameVideosViewModel = gameVideosViewModel)
        }
        composeTestRule.onNodeWithText("GTA Online: Tiny Racers Trailer").performScrollTo()
        composeTestRule.onNodeWithText("GTA Online: Tiny Racers Trailer").assertIsDisplayed()
    }

    @Test
    fun game_videos_in_playlist_should_be_clickable() {
        every { gameVideosViewModel.uiState() } answers {
            MutableStateFlow(GameVideosState(ScreenState.Success, FakeGamesData.getFakeGameVideos(), null))
        }
        composeTestRule.setContent {
            GameVideosScreen(gameId = 1, navigateBack = { }, gameVideosViewModel = gameVideosViewModel)
        }
        composeTestRule.onAllNodesWithTag("TrailerParent").assertCountEquals(3)[0].assertHasClickAction()
    }

    @Test
    fun getting_no_videos_for_the_game_should_show_toast_and_exit_screen() {
        every { gameVideosViewModel.uiState() } answers {
            MutableStateFlow(GameVideosState(ScreenState.Success, GameVideosEntity(), null))
        }
        every { gameVideosViewModel.uiSideEffect() } answers {
            flow { emit(GameVideosSideEffect.ShowNoGameVideosToast) }
        }
        var navigateBack = false
        composeTestRule.setContent {
            GameVideosScreen(gameId = 123, navigateBack = { navigateBack = true }, gameVideosViewModel = gameVideosViewModel)
        }
        Assert.assertTrue(navigateBack)
    }

    @Test
    fun getting_error_on_fetching_game_videos_should_show_toast_and_exit_screen() {
        every { gameVideosViewModel.uiState() } answers {
            MutableStateFlow(GameVideosState(ScreenState.Error, null, ErrorRecord.ServerError))
        }
        every { gameVideosViewModel.uiSideEffect() } answers {
            flow { emit(GameVideosSideEffect.ShowGameVideosErrorToast) }
        }
        var navigateBack = false
        composeTestRule.setContent {
            GameVideosScreen(gameId = 123, navigateBack = { navigateBack = true }, gameVideosViewModel = gameVideosViewModel)
        }
        Assert.assertTrue(navigateBack)
    }

}