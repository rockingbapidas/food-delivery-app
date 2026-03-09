package com.example.zomato.data.api

import com.example.zomato.data.api.model.UserDto
import retrofit2.http.*

interface AuthApi {
    @POST("auth/login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): UserDto

    @POST("auth/register")
    suspend fun register(
        @Query("name") name: String,
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("phoneNumber") phoneNumber: String
    ): UserDto

    @POST("auth/logout")
    suspend fun logout()

    @POST("auth/reset-password")
    suspend fun resetPassword(@Query("email") email: String)

    @PUT("auth/password")
    suspend fun updatePassword(
        @Query("currentPassword") currentPassword: String,
        @Query("newPassword") newPassword: String
    )

    @POST("auth/verify-email")
    suspend fun verifyEmail(@Query("token") token: String)

    @POST("auth/resend-verification")
    suspend fun resendVerificationEmail()
} 