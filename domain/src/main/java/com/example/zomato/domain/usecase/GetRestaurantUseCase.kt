package com.example.zomato.domain.usecase

import com.example.zomato.domain.model.Restaurant
import com.example.zomato.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRestaurantUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    operator fun invoke(restaurantId: String): Flow<Restaurant> {
        return repository.getRestaurantById(restaurantId)
    }
} 