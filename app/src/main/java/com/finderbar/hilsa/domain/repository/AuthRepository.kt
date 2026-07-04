package com.finderbar.hilsa.domain.repository

import com.finderbar.hilsa.core.common.Result
import com.finderbar.hilsa.domain.model.User

/**
 * Interface defining authentication operations.
 * Following Clean Architecture, this interface resides in the Domain layer.
 */
interface AuthRepository {
    /**
     * Authenticates a user with email and password.
     */
    suspend fun login(email: String, password: String): Result<User>

    /**
     * Terminate the current user session and clear local credentials.
     */
    suspend fun logout()

    /**
     * Retrieves the current user session if available.
     */
    suspend fun getSession(): User?
}
