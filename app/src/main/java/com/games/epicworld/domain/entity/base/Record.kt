package com.games.epicworld.domain.entity.base


data class Record<out R>(
    val data: R?,
    val error: ErrorRecord?
)
