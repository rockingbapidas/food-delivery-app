package com.example.zomato.domain.usecase

import com.example.zomato.domain.model.Restaurant
import com.example.zomato.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    operator fun invoke(query: String): Flow<List<Restaurant>> {
        return repository.searchRestaurants(query)
    }
} 