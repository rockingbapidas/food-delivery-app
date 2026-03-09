package com.example.zomato.domain.repository

import com.example.zomato.domain.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    fun getMenuItems(restaurantId: String): Flow<List<MenuItem>>
    fun getMenuItemsByCategory(restaurantId: String, category: String): Flow<List<MenuItem>>
    fun searchMenuItems(restaurantId: String, query: String): Flow<List<MenuItem>>
    suspend fun refreshMenuItems(restaurantId: String)
    suspend fun addMenuItem(restaurantId: String, menuItem: MenuItem)
    suspend fun updateMenuItem(restaurantId: String, menuItem: MenuItem)
    suspend fun deleteMenuItem(restaurantId: String, menuItemId: String)
} 