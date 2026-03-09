package com.example.zomato.domain.repository

import com.example.zomato.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {
    fun getRestaurants(): Flow<List<Restaurant>>
    fun getRestaurantById(id: String): Flow<Restaurant>
    fun searchRestaurants(query: String): Flow<List<Restaurant>>
    fun getPopularRestaurants(): Flow<List<Restaurant>>
    fun getRestaurantsByCuisine(cuisine: String): Flow<List<Restaurant>>
    fun getRestaurantsByLocation(
        latitude: Double,
        longitude: Double,
        radius: Double
    ): Flow<List<Restaurant>>
    suspend fun refreshRestaurants()
} 