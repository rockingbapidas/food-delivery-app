package com.example.zomato.domain.repository

import com.example.zomato.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<User?>
    val isAuthenticated: Flow<Boolean>

    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(
        name: String,
        email: String,
        password: String,
        phoneNumber: String
    ): Result<User>
    suspend fun logout(): Result<Unit>
    suspend fun resetPassword(email: String): Result<Unit>
    suspend fun updatePassword(currentPassword: String, newPassword: String): Result<Unit>
    suspend fun verifyEmail(token: String): Result<Unit>
    suspend fun resendVerificationEmail(): Result<Unit>
} 