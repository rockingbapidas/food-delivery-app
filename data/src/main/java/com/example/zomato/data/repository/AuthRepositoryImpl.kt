package com.example.zomato.data.repository

import com.example.zomato.domain.model.User
import com.example.zomato.domain.repository.AuthRepository
import com.example.zomato.data.api.AuthApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    private val _currentUser = MutableStateFlow<User?>(null)

    override val currentUser: Flow<User?> = _currentUser

    override val isAuthenticated: Flow<Boolean> = _currentUser.map { it != null }

    override suspend fun login(email: String, password: String): Result<User> = runCatching {
        val response = api.login(email, password)
        val user = response.toDomain()
        _currentUser.value = user
        user
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        phoneNumber: String
    ): Result<User> = runCatching {
        val response = api.register(name, email, password, phoneNumber)
        val user = response.toDomain()
        _currentUser.value = user
        user
    }

    override suspend fun logout(): Result<Unit> = runCatching {
        api.logout()
        _currentUser.value = null
    }

    override suspend fun resetPassword(email: String): Result<Unit> = runCatching {
        api.resetPassword(email)
    }

    override suspend fun updatePassword(
        currentPassword: String,
        newPassword: String
    ): Result<Unit> = runCatching {
        api.updatePassword(currentPassword, newPassword)
    }

    override suspend fun verifyEmail(token: String): Result<Unit> = runCatching {
        api.verifyEmail(token)
    }

    override suspend fun resendVerificationEmail(): Result<Unit> = runCatching {
        api.resendVerificationEmail()
    }
} 