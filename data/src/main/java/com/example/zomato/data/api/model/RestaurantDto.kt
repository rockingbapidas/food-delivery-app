package com.example.zomato.data.api.model

import com.example.zomato.domain.model.Restaurant
import com.example.zomato.domain.model.MenuItem

data class RestaurantDto(
    val id: String,
    val name: String,
    val cuisine: String,
    val rating: Double,
    val address: String,
    val imageUrl: String,
    val menuItems: List<MenuItemDto>,
    val isOpen: Boolean = true,
    val deliveryTime: Int = 30,
    val minimumOrder: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val offers: List<String> = emptyList()
) {
    fun toDomain(): Restaurant {
        return Restaurant(
            id = id,
            name = name,
            cuisine = cuisine,
            rating = rating,
            address = address,
            imageUrl = imageUrl,
            menuItems = menuItems.map { it.toDomain() },
            isOpen = isOpen,
            deliveryTime = deliveryTime,
            minimumOrder = minimumOrder,
            deliveryFee = deliveryFee,
            offers = offers
        )
    }

    companion object {
        fun fromDomain(restaurant: Restaurant) = RestaurantDto(
            id = restaurant.id,
            name = restaurant.name,
            cuisine = restaurant.cuisine,
            rating = restaurant.rating,
            address = restaurant.address,
            imageUrl = restaurant.imageUrl,
            menuItems = restaurant.menuItems.map { MenuItemDto.fromDomain(it) },
            isOpen = restaurant.isOpen,
            deliveryTime = restaurant.deliveryTime,
            minimumOrder = restaurant.minimumOrder,
            deliveryFee = restaurant.deliveryFee,
            offers = restaurant.offers
        )
    }
} 