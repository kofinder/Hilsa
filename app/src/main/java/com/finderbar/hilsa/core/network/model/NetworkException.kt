package com.finderbar.hilsa.core.network.model

/**
 * Author: Ko Thein (Nathan Mratt)
 * Created: 7/4/2026
 * Project: Hilsa
 */
sealed class NetworkException : Exception() {

    /**
     * No internet connection available.
     */
    data object NoInternet : NetworkException()

    /**
     * Request timed out while waiting for response.
     */
    data object Timeout : NetworkException()

    /**
     * Server returned an HTTP error response.
     *
     * @param code HTTP status code (e.g. 400, 401, 500)
     * @param message Server-provided or mapped error message
     */
    data class HttpError(
        val code: Int,
        override val message: String?
    ) : NetworkException()

    /**
     * Unexpected or unknown error occurred.
     */
    data class Unknown(
        val error: Throwable
    ) : NetworkException()
}