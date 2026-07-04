package com.finderbar.hilsa.infrastructure.repository

import com.finderbar.hilsa.core.common.Result
import com.finderbar.hilsa.core.database.dao.UserDao
import com.finderbar.hilsa.core.database.entity.UserEntity
import com.finderbar.hilsa.core.network.api.AuthApi
import com.finderbar.hilsa.core.network.dto.LoginRequest
import com.finderbar.hilsa.core.network.util.SafeApiCall
import com.finderbar.hilsa.domain.model.User
import com.finderbar.hilsa.domain.repository.AuthRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val userDao: UserDao
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        val result = SafeApiCall.execute {
            authApi.login(LoginRequest(email, password))
        }

        return when (result) {
            is Result.Success -> {
                val response = result.data
                val user = User(
                    id = response.user.id,
                    email = response.user.email,
                    name = response.user.name,
                    token = response.token
                )
                
                userDao.insertUser(
                    UserEntity(
                        id = user.id,
                        email = user.email,
                        name = user.name,
                        token = user.token
                    )
                )
                Result.Success(user)
            }
            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    override suspend fun logout() {
        userDao.clearUser()
    }

    override suspend fun getSession(): User? {
        return userDao.getUser().firstOrNull()?.let {
            User(it.id, it.email, it.name, it.token)
        }
    }
}