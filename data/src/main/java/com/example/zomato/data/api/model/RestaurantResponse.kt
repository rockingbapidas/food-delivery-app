package com.example.zomato.data.api.model

import com.example.zomato.domain.model.Restaurant

data class RestaurantResponse(
    val id: String,
    val name: String,
    val imageUrl: String,
    val rating: Double,
    val cuisine: String,
    val address: String,
    val deliveryTime: Int,
    val deliveryFee: Double,
    val minOrder: Double,
    val isOpen: Boolean,
    val offers: List<String>,
    val menuItems: List<MenuItemDto>
) {
    fun toDomain(): Restaurant {
        return Restaurant(
            id = id,
            name = name,
            imageUrl = imageUrl,
            rating = rating,
            cuisine = cuisine,
            address = address,
            deliveryTime = deliveryTime,
            deliveryFee = deliveryFee,
            minimumOrder = minOrder,
            isOpen = isOpen,
            offers = offers,
            menuItems = menuItems.map { it.toDomain() }
        )
    }
} 