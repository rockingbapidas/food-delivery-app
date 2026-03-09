package com.example.zomato.domain.usecase

import com.example.zomato.domain.model.Restaurant
import com.example.zomato.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    operator fun invoke(): Flow<List<Restaurant>> {
        return repository.getRestaurants()
    }
} 