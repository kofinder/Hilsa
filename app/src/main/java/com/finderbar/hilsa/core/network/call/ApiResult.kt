package com.finderbar.hilsa.core.network.call

import com.finderbar.hilsa.core.network.model.NetworkException

/**
 * Author: Ko Thein (Nathan Mratt)
 * Created: 7/4/2026
 * Project: Hilsa
 */
sealed class ApiResult<out T> {

    /**
     * Successful response containing data.
     */
    data class Success<T>(
        val data: T
    ) : ApiResult<T>()

    /**
     * Failed response containing structured error.
     */
    data class Error(
        val exception: NetworkException
    ) : ApiResult<Nothing>()

    /**
     * Optional loading state for UI handling.
     */
    data object Loading : ApiResult<Nothing>()
}