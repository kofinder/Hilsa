package com.finderbar.hilsa.core.network.util

sealed class NetworkException : Exception() {
    data object NoInternet : NetworkException()
    data object Timeout : NetworkException()
    data class HttpError(
        val code: Int,
        override val message: String?
    ) : NetworkException()
    data class Unknown(
        val error: Throwable
    ) : NetworkException()
}