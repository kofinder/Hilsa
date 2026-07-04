package com.finderbar.hilsa.infrastructure.mapper

import com.finderbar.hilsa.core.database.entity.UserEntity
import com.finderbar.hilsa.core.network.dto.LoginResponse
import com.finderbar.hilsa.domain.model.User
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Mapper class for converting User-related data between different layers.
 */
@Singleton
class UserMapper @Inject constructor() {

    /**
     * Converts [LoginResponse] DTO from the network layer to the [User] domain model.
     */
    fun toDomain(dto: LoginResponse): User {
        return User(
            id = dto.user.id,
            email = dto.user.email,
            name = dto.user.name,
            token = dto.token
        )
    }

    /**
     * Converts [UserEntity] from the database layer to the [User] domain model.
     */
    fun toDomain(entity: UserEntity): User {
        return User(
            id = entity.id,
            email = entity.email,
            name = entity.name,
            token = entity.token
        )
    }

    /**
     * Converts [User] domain model to the [UserEntity] for database storage.
     */
    fun toEntity(domain: User): UserEntity {
        return UserEntity(
            id = domain.id,
            email = domain.email,
            name = domain.name,
            token = domain.token
        )
    }
}
