package com.finderbar.hilsa.domain.usecase.auth

import com.finderbar.hilsa.core.common.Result
import com.finderbar.hilsa.domain.model.User
import com.finderbar.hilsa.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Use case for performing the login operation.
 * Encapsulates the business logic for user authentication.
 *
 * @property authRepository The repository providing authentication services.
 */
class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Executes the login operation.
     *
     * @param email User's email address.
     * @param password User's password.
     * @return A [Result] containing the authenticated [User] on success.
     */
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return authRepository.login(email, password)
    }
}
