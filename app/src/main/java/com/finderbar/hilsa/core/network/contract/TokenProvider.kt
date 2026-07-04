package com.finderbar.hilsa.core.network.contract

/**
 * Author: Ko Thein (Nathan Mratt)
 * Created: 7/4/2026
 * Project: Hilsa
 */
interface  TokenProvider {
    /**
     * Returns current access token.
     * Can be null if user is not logged in.
     */
    suspend fun getToken(): String?

    /**
     * Optional: Clear token on logout or auth failure.
     */
    suspend fun clearToken()
}