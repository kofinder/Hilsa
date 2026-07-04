package com.finderbar.hilsa.core.common

import com.finderbar.hilsa.core.network.util.NetworkException

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: NetworkException) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}