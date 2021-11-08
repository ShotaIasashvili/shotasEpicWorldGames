package com.games.epicworld.presentation.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import com.games.epicworld.presentation.base.BaseActivity


@ExperimentalAnimationApi
@SuppressLint("CustomSplashScreen")
@ExperimentalFoundationApi
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}