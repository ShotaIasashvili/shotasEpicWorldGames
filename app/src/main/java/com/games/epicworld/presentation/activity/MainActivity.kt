package com.games.epicworld.presentation.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import com.games.epicworld.presentation.EpicWorldApp
import com.games.epicworld.presentation.base.BaseActivity
import com.games.epicworld.presentation.theme.EpicWorldTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EpicWorldTheme {
                EpicWorldApp()
            }
        }
    }
}