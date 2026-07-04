package com.finderbar.hilsa.domain.repository

import com.finderbar.hilsa.core.common.Result
import com.finderbar.hilsa.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun logout()
    suspend fun getSession(): User?
}