package com.example.zomato.domain.repository

import com.example.zomato.domain.model.User
import com.example.zomato.domain.model.Address
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getCurrentUser(): Flow<User>
    suspend fun updateUser(user: User): Result<User>
    suspend fun addAddress(address: Address): Result<Address>
    suspend fun updateAddress(address: Address): Result<Address>
    suspend fun deleteAddress(addressId: String): Result<Unit>
    suspend fun setDefaultAddress(addressId: String): Result<Unit>
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(name: String, email: String, password: String, phoneNumber: String): Result<User>
    suspend fun logout(): Result<Unit>
} 