package com.example.zomato.data.api

import com.example.zomato.data.api.model.UserDto
import com.example.zomato.data.api.model.AddressDto
import retrofit2.http.*

interface UserApi {
    @GET("user/current")
    suspend fun getCurrentUser(): UserDto

    @PUT("user")
    suspend fun updateUser(@Body user: UserDto): UserDto

    @POST("user/addresses")
    suspend fun addAddress(@Body address: AddressDto): AddressDto

    @PUT("user/addresses/{id}")
    suspend fun updateAddress(
        @Path("id") id: String,
        @Body address: AddressDto
    ): AddressDto

    @DELETE("user/addresses/{id}")
    suspend fun deleteAddress(@Path("id") id: String)

    @PUT("user/addresses/{id}/default")
    suspend fun setDefaultAddress(@Path("id") id: String)

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
} 