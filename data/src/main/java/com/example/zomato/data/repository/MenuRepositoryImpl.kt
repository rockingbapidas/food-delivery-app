package com.example.zomato.data.repository

import com.example.zomato.data.api.MenuApi
import com.example.zomato.data.db.MenuItemDao
import com.example.zomato.data.db.entity.MenuItemEntity
import com.example.zomato.domain.model.MenuItem
import com.example.zomato.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val api: MenuApi,
    private val dao: MenuItemDao
) : MenuRepository {

    override fun getMenuItems(restaurantId: String): Flow<List<MenuItem>> {
        return dao.getMenuItems(restaurantId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getMenuItemsByCategory(restaurantId: String, category: String): Flow<List<MenuItem>> {
        return dao.getMenuItemsByCategory(restaurantId, category).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun searchMenuItems(restaurantId: String, query: String): Flow<List<MenuItem>> {
        return dao.searchMenuItems(restaurantId, query).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun refreshMenuItems(restaurantId: String) {
        try {
            val menuItems = api.getMenuItems(restaurantId)
            dao.insertMenuItems(menuItems.map { MenuItemEntity.fromDomain(it.toDomain()) })
        } catch (e: Exception) {
            // Handle error
        }
    }

    override suspend fun addMenuItem(restaurantId: String, menuItem: MenuItem) {
        // TODO: Implement API call and update DB
    }

    override suspend fun updateMenuItem(restaurantId: String, menuItem: MenuItem) {
        // TODO: Implement API call and update DB
    }

    override suspend fun deleteMenuItem(restaurantId: String, menuItemId: String) {
        // TODO: Implement API call and update DB
    }
}
