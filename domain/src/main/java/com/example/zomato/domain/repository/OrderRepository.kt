package com.example.zomato.domain.repository

import com.example.zomato.domain.model.Order
import com.example.zomato.domain.model.OrderStatus
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getOrders(restaurantId: String): Flow<List<Order>>
    fun getOrderById(orderId: String): Flow<Order?>
    fun getActiveOrders(): Flow<List<Order>>
    fun getOrderHistory(): Flow<List<Order>>
    suspend fun placeOrder(order: Order): Result<Order>
    suspend fun cancelOrder(orderId: String): Result<Unit>
    suspend fun updateOrderStatus(orderId: String, status: OrderStatus): Result<Unit>
    suspend fun trackOrder(orderId: String): Flow<Order>
    suspend fun rateOrder(orderId: String, rating: Double, feedback: String?): Result<Unit>
}