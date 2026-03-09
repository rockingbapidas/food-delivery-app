package com.example.zomato.data.api.model

import com.example.zomato.domain.model.MenuItem

data class MenuItemDto(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val isVegetarian: Boolean = false,
    val isSpicy: Boolean = false,
    val restaurantId: String,
    val isAvailable: Boolean = true
) {
    fun toDomain(): MenuItem {
        return MenuItem(
            id = id,
            name = name,
            description = description,
            price = price,
            imageUrl = imageUrl,
            category = category,
            isVegetarian = isVegetarian,
            isSpicy = isSpicy,
            isAvailable = isAvailable,
            restaurantId = restaurantId
        )
    }

    companion object {
        fun fromDomain(menuItem: MenuItem) = MenuItemDto(
            id = menuItem.id,
            name = menuItem.name,
            description = menuItem.description,
            price = menuItem.price,
            imageUrl = menuItem.imageUrl,
            category = menuItem.category,
            isVegetarian = menuItem.isVegetarian,
            isSpicy = menuItem.isSpicy,
            isAvailable = menuItem.isAvailable,
            restaurantId = menuItem.restaurantId
        )
    }
} 