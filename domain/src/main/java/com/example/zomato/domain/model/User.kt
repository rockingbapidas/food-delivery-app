package com.example.zomato.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val addresses: List<Address>,
    val defaultAddressId: String? = null,
    val role: UserRole = UserRole.CUSTOMER
)

data class Address(
    val id: String,
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val postalCode: String,
    val isDefault: Boolean = false,
    val latitude: Double? = null,
    val longitude: Double? = null
)

enum class UserRole {
    CUSTOMER,
    DELIVERY_PERSON,
    RESTAURANT_OWNER
}