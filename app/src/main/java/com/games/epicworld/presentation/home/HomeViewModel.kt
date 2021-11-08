package com.games.epicworld.presentation.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.games.epicworld.domain.entity.base.ErrorRecord
import com.games.epicworld.domain.entity.games.GameResultsEntity
import com.games.epicworld.domain.interactor.GamesSource
import com.games.epicworld.presentation.base.BaseViewModel
import com.games.epicworld.presentation.base.ScreenState
import com.games.epicworld.presentation.home.ui.HomeSideEffect
import com.games.epicworld.presentation.home.ui.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val gamesSource: GamesSource): BaseViewModel<HomeState, HomeSideEffect>() {

    override fun createInitialState(): HomeState = HomeState(ScreenState.Loading, null, null)

    override fun initData() = intent {
        val result = getAllGames()
        reduce {
            state.copy(screenState = ScreenState.Success, games = result.flow, error = null)
        }
    }

    fun handlePaginationDataError() = intent {
        reduce {
            state.copy(screenState = ScreenState.Error,
                games = null,
                error = ErrorRecord.ServerError
            )
        }
    }

    fun handlePaginationAppendError(message: String, action: String) = intent {
        postSideEffect(HomeSideEffect.ShowSnackBar(message = message, action = action))
    }

    private fun getAllGames(): Pager<Int, GameResultsEntity> {
        return Pager(PagingConfig(50)) {
            gamesSource
        }
    }
}