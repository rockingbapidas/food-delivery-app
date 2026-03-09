package com.example.zomato.di

import android.content.Context
import androidx.room.Room
import com.example.zomato.data.db.AppDatabase
import com.example.zomato.data.db.MenuItemDao
import com.example.zomato.data.db.RestaurantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "zomato.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRestaurantDao(db: AppDatabase): RestaurantDao {
        return db.restaurantDao()
    }

    @Provides
    @Singleton
    fun provideMenuItemDao(db: AppDatabase): MenuItemDao {
        return db.menuItemDao()
    }
}
