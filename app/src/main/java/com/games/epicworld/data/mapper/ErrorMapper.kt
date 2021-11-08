package com.games.epicworld.data.mapper

import android.util.Log
import com.games.epicworld.domain.entity.base.ErrorRecord
import com.games.epicworld.domain.entity.base.Record
import com.games.epicworld.remote.RemoteException


class ErrorMapper {

    fun <T>mapErrorRecord(e: RemoteException): Record<T> {
        Log.e(ErrorMapper::class.simpleName, e.message.toString())
        val errorRecord: ErrorRecord = when(e) {
            is RemoteException.ClientError -> ErrorRecord.ClientError
            is RemoteException.ServerError -> ErrorRecord.ServerError
            is RemoteException.NoNetworkError -> ErrorRecord.NetworkError
            else -> ErrorRecord.GenericError
        }
        return Record(null, errorRecord)
    }
}