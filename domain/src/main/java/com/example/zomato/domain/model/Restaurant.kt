package com.example.zomato.domain.model

data class Restaurant(
    val id: String,
    val name: String,
    val cuisine: String,
    val rating: Double,
    val address: String,
    val imageUrl: String,
    val menuItems: List<MenuItem>,
    val isOpen: Boolean = true,
    val deliveryTime: Int = 30,
    val minimumOrder: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val offers: List<String> = emptyList()
)