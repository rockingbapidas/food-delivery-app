package com.example.zomato.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.zomato.data.api.model.MenuItemDto
import com.example.zomato.domain.model.MenuItem

@Entity(tableName = "menu_items")
data class MenuItemEntity(
    @PrimaryKey
    val id: String,
    val restaurantId: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val isVegetarian: Boolean,
    val isSpicy: Boolean,
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
        fun fromDomain(menuItem: MenuItem) = MenuItemEntity(
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