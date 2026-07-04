package com.finderbar.hilsa.infrastructure.repository

import com.finderbar.hilsa.core.common.Result
import com.finderbar.hilsa.core.database.dao.UserDao
import com.finderbar.hilsa.core.network.api.AuthApi
import com.finderbar.hilsa.core.network.dto.LoginRequest
import com.finderbar.hilsa.core.network.util.SafeApiCall
import com.finderbar.hilsa.domain.model.User
import com.finderbar.hilsa.domain.repository.AuthRepository
import com.finderbar.hilsa.infrastructure.mapper.UserMapper
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of [AuthRepository] that coordinates data from the network and database.
 *
 * @property authApi The Retrofit API service for authentication.
 * @property userDao The Room DAO for user data.
 * @property userMapper The mapper for converting between data layers.
 */
@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val userDao: UserDao,
    private val userMapper: UserMapper
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        val responseResult = SafeApiCall.execute {
            authApi.login(LoginRequest(email, password))
        }

        return when (responseResult) {
            is Result.Success -> {
                val user = userMapper.toDomain(responseResult.data)
                userDao.insertUser(userMapper.toEntity(user))
                Result.Success(user)
            }
            is Result.Error -> Result.Error(responseResult.exception)
            Result.Loading -> Result.Loading
        }
    }

    override suspend fun logout() {
        userDao.clearUser()
    }

    override suspend fun getSession(): User? {
        return userDao.getUser().firstOrNull()?.let { userMapper.toDomain(it) }
    }
}
