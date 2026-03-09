package com.example.zomato.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.zomato.domain.model.MenuItem
import com.example.zomato.domain.model.Restaurant

@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey
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
    val isPopular: Boolean = false
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
            menuItems = listOf(),
        )
    }

    companion object {
        fun fromDomain(restaurant: Restaurant, isPopular: Boolean = false): RestaurantEntity {
            return RestaurantEntity(
                id = restaurant.id,
                name = restaurant.name,
                imageUrl = restaurant.imageUrl,
                rating = restaurant.rating,
                cuisine = restaurant.cuisine,
                address = restaurant.address,
                deliveryTime = restaurant.deliveryTime,
                deliveryFee = restaurant.deliveryFee,
                minOrder = restaurant.minimumOrder,
                isOpen = restaurant.isOpen,
                offers = restaurant.offers,
                isPopular = isPopular
            )
        }
    }
} 