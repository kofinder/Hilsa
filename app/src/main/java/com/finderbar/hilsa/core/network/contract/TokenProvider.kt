package com.finderbar.hilsa.core.network.contract

/**
 * Interface for providing and managing authentication tokens.
 */
interface TokenProvider {
    /**
     * Retrieves the current access token.
     * @return The token string if available, null otherwise.
     */
    suspend fun getToken(): String?

    /**
     * Clears the stored authentication token.
     */
    suspend fun clearToken()
}
