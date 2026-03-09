package com.example.zomato.data.di

import com.example.zomato.data.repository.RestaurantRepositoryImpl
import com.example.zomato.data.repository.OrderRepositoryImpl
import com.example.zomato.data.repository.UserRepositoryImpl
import com.example.zomato.data.repository.CartRepositoryImpl
import com.example.zomato.data.repository.AuthRepositoryImpl
import com.example.zomato.data.repository.MenuRepositoryImpl
import com.example.zomato.domain.repository.OrderRepository
import com.example.zomato.domain.repository.RestaurantRepository
import com.example.zomato.domain.repository.MenuRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRestaurantRepository(
        restaurantRepositoryImpl: RestaurantRepositoryImpl
    ): RestaurantRepository

    @Binds
    @Singleton
    abstract fun bindOrderRepository(
        orderRepositoryImpl: OrderRepositoryImpl
    ): OrderRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): com.example.zomato.domain.repository.UserRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): com.example.zomato.domain.repository.CartRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): com.example.zomato.domain.repository.AuthRepository

    @Binds
    @Singleton
    abstract fun bindMenuRepository(
        menuRepositoryImpl: MenuRepositoryImpl
    ): MenuRepository
}
