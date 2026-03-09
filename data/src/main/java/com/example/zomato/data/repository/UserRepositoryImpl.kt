package com.example.zomato.data.repository

import com.example.zomato.domain.model.User
import com.example.zomato.domain.model.Address
import com.example.zomato.domain.repository.UserRepository
import com.example.zomato.data.api.UserApi
import com.example.zomato.data.api.model.UserDto
import com.example.zomato.data.api.model.AddressDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {

    override fun getCurrentUser(): Flow<User> = flow {
        val response = api.getCurrentUser()
        emit(response.toDomain())
    }

    override suspend fun updateUser(user: User): Result<User> = runCatching {
        val response = api.updateUser(UserDto.fromDomain(user))
        response.toDomain()
    }

    override suspend fun addAddress(address: Address): Result<Address> = runCatching {
        val response = api.addAddress(AddressDto.fromDomain(address))
        response.toDomain()
    }

    override suspend fun updateAddress(address: Address): Result<Address> = runCatching {
        val response = api.updateAddress(address.id, AddressDto.fromDomain(address))
        response.toDomain()
    }

    override suspend fun deleteAddress(addressId: String): Result<Unit> = runCatching {
        api.deleteAddress(addressId)
    }

    override suspend fun setDefaultAddress(addressId: String): Result<Unit> = runCatching {
        api.setDefaultAddress(addressId)
    }

    override suspend fun login(email: String, password: String): Result<User> = runCatching {
        val response = api.login(email, password)
        response.toDomain()
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        phoneNumber: String
    ): Result<User> = runCatching {
        val response = api.register(name, email, password, phoneNumber)
        response.toDomain()
    }

    override suspend fun logout(): Result<Unit> = runCatching {
        api.logout()
    }
} 