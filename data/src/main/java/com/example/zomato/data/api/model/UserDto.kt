package com.example.zomato.data.api.model

import com.example.zomato.domain.model.User
import com.example.zomato.domain.model.Address

data class UserDto(
    val id: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val addresses: List<AddressDto>,
    val defaultAddressId: String? = null
) {
    fun toDomain(): User {
        return User(
            id = id,
            name = name,
            email = email,
            phoneNumber = phoneNumber,
            addresses = addresses.map { it.toDomain() },
            defaultAddressId = defaultAddressId
        )
    }

    companion object {
        fun fromDomain(user: User): UserDto {
            return UserDto(
                id = user.id,
                name = user.name,
                email = user.email,
                phoneNumber = user.phoneNumber,
                addresses = user.addresses.map { AddressDto.fromDomain(it) },
                defaultAddressId = user.defaultAddressId
            )
        }
    }
}

data class AddressDto(
    val id: String,
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val postalCode: String,
    val isDefault: Boolean = false,
    val latitude: Double? = null,
    val longitude: Double? = null
) {
    fun toDomain(): Address {
        return Address(
            id = id,
            street = street,
            city = city,
            state = state,
            country = country,
            postalCode = postalCode,
            isDefault = isDefault,
            latitude = latitude,
            longitude = longitude
        )
    }

    companion object {
        fun fromDomain(address: Address): AddressDto {
            return AddressDto(
                id = address.id,
                street = address.street,
                city = address.city,
                state = address.state,
                country = address.country,
                postalCode = address.postalCode,
                isDefault = address.isDefault,
                latitude = address.latitude,
                longitude = address.longitude
            )
        }
    }
} 