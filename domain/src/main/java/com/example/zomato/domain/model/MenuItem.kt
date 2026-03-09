package com.example.zomato.domain.model

data class MenuItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val isVegetarian: Boolean,
    val isSpicy: Boolean,
    val restaurantId: String,
    val isAvailable: Boolean = true
) 