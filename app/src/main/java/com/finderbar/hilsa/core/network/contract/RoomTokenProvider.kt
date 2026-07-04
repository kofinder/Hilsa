package com.finderbar.hilsa.core.network.contract

import com.finderbar.hilsa.core.database.dao.UserDao
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomTokenProvider @Inject constructor(
    private val userDao: UserDao
) : TokenProvider {

    override suspend fun getToken(): String? {
        return userDao.getUser().firstOrNull()?.token
    }

    override suspend fun clearToken() {
        userDao.clearUser()
    }
}