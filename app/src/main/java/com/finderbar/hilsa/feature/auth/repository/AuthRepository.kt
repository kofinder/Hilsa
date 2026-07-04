package com.finderbar.hilsa.feature.auth.repository

import com.finderbar.hilsa.core.database.dao.UserDao
import com.finderbar.hilsa.core.database.model.UserEntity
import com.finderbar.hilsa.core.network.api.AuthApi
import com.finderbar.hilsa.core.network.call.ApiResult
import com.finderbar.hilsa.core.network.call.SafeApiCall
import com.finderbar.hilsa.core.network.model.LoginRequest
import com.finderbar.hilsa.core.network.model.LoginResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApi: AuthApi,
    private val userDao: UserDao
) {

    suspend fun login(request: LoginRequest): ApiResult<LoginResponse> {
        val result = SafeApiCall.execute(
            apiCall = { authApi.login(request) }
        )

        if (result is ApiResult.Success) {
            val response = result.data
            userDao.insertUser(
                UserEntity(
                    id = response.user.id,
                    email = response.user.email,
                    name = response.user.name,
                    token = response.token
                )
            )
        }

        return result
    }
}