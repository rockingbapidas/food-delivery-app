package com.example.zomato.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.zomato.data.db.entity.MenuItemEntity
import com.example.zomato.data.db.entity.RestaurantEntity

@Database(
    entities = [
        RestaurantEntity::class,
        MenuItemEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
    abstract fun menuItemDao(): MenuItemDao
}
