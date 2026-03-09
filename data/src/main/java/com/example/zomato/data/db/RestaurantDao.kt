package com.example.zomato.data.db

import androidx.room.*
import com.example.zomato.data.db.entity.RestaurantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurants")
    fun getAllRestaurants(): Flow<List<RestaurantEntity>>

    @Query("SELECT * FROM restaurants WHERE id = :id")
    fun getRestaurantById(id: String): Flow<RestaurantEntity?>

    @Query("SELECT * FROM restaurants WHERE name LIKE '%' || :query || '%'")
    fun searchRestaurants(query: String): Flow<List<RestaurantEntity>>

    @Query("SELECT * FROM restaurants WHERE isPopular = 1")
    fun getPopularRestaurants(): Flow<List<RestaurantEntity>>

    @Query("SELECT * FROM restaurants WHERE cuisine = :cuisine")
    fun getRestaurantsByCuisine(cuisine: String): Flow<List<RestaurantEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurants: List<RestaurantEntity>)

    @Query("DELETE FROM restaurants")
    suspend fun deleteAllRestaurants()
} 