package com.example.zomato.data.repository

import com.example.zomato.data.api.RestaurantApi
import com.example.zomato.data.db.RestaurantDao
import com.example.zomato.domain.model.Restaurant
import com.example.zomato.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantApi,
    private val dao: RestaurantDao
) : RestaurantRepository {

    override fun getRestaurants(): Flow<List<Restaurant>> = flow {
        val response = api.getRestaurants()
        emit(response.map { it.toDomain() })
    }

    override fun getRestaurantById(id: String): Flow<Restaurant> = flow {
        val response = api.getRestaurantById(id)
        emit(response.toDomain())
    }

    override fun searchRestaurants(query: String): Flow<List<Restaurant>> = flow {
        val response = api.searchRestaurants(query)
        emit(response.map { it.toDomain() })
    }

    override fun getPopularRestaurants(): Flow<List<Restaurant>> {
        TODO("Not yet implemented")
    }

    override fun getRestaurantsByCuisine(cuisine: String): Flow<List<Restaurant>> = flow {
        val response = api.getRestaurantsByCuisine(cuisine)
        emit(response.map { it.toDomain() })
    }

    override suspend fun refreshRestaurants() {
        TODO("Not yet implemented")
    }

    override fun getRestaurantsByLocation(
        latitude: Double,
        longitude: Double,
        radius: Double
    ): Flow<List<Restaurant>> = flow {
        val response = api.getRestaurantsByLocation(latitude, longitude, radius)
        emit(response.map { it.toDomain() })
    }
} 