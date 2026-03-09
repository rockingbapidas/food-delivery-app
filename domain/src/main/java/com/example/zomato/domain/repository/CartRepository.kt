package com.example.zomato.domain.repository

import com.example.zomato.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun addToCart(menuItemId: String, quantity: Int = 1): Result<CartItem>
    suspend fun updateQuantity(menuItemId: String, quantity: Int): Result<CartItem>
    suspend fun removeFromCart(menuItemId: String): Result<Unit>
    suspend fun clearCart(): Result<Unit>
    suspend fun placeOrder(): Result<String> // Returns order ID
} 