package com.example.zomato.data.repository

import com.example.zomato.domain.model.CartItem
import com.example.zomato.domain.repository.CartRepository
import com.example.zomato.data.api.CartApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val api: CartApi
) : CartRepository {

    override fun getCartItems(): Flow<List<CartItem>> = flow {
        val response = api.getCartItems()
        emit(response.map { it.toDomain() })
    }

    override suspend fun addToCart(menuItemId: String, quantity: Int): Result<CartItem> = runCatching {
        val response = api.addToCart(menuItemId, quantity)
        response.toDomain()
    }

    override suspend fun updateQuantity(menuItemId: String, quantity: Int): Result<CartItem> = runCatching {
        val response = api.updateQuantity(menuItemId, quantity)
        response.toDomain()
    }

    override suspend fun removeFromCart(menuItemId: String): Result<Unit> = runCatching {
        api.removeFromCart(menuItemId)
    }

    override suspend fun clearCart(): Result<Unit> = runCatching {
        api.clearCart()
    }

    override suspend fun placeOrder(): Result<String> = runCatching {
        api.placeOrder()
    }
} 