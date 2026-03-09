package com.example.zomato.domain.usecase

import com.example.zomato.domain.model.MenuItem
import com.example.zomato.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMenuItemsUseCase @Inject constructor(
    private val repository: MenuRepository
) {
    operator fun invoke(restaurantId: String): Flow<List<MenuItem>> {
        return repository.getMenuItems(restaurantId)
    }
} 