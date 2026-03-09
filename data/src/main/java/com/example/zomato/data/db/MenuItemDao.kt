package com.example.zomato.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zomato.data.db.entity.MenuItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM menu_items WHERE restaurantId = :restaurantId")
    fun getMenuItems(restaurantId: String): Flow<List<MenuItemEntity>>

    @Query("SELECT * FROM menu_items WHERE restaurantId = :restaurantId AND category = :category")
    fun getMenuItemsByCategory(restaurantId: String, category: String): Flow<List<MenuItemEntity>>

    @Query("SELECT * FROM menu_items WHERE restaurantId = :restaurantId AND name LIKE '%' || :query || '%'")
    fun searchMenuItems(restaurantId: String, query: String): Flow<List<MenuItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuItems(menuItems: List<MenuItemEntity>)

    @Query("DELETE FROM menu_items WHERE restaurantId = :restaurantId")
    suspend fun deleteMenuItems(restaurantId: String)
} 