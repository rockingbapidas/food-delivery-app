package com.example.zomato.data.repository

import com.example.zomato.domain.model.Order
import com.example.zomato.domain.model.OrderStatus
import com.example.zomato.data.api.OrderApi
import com.example.zomato.data.api.model.OrderDto
import com.example.zomato.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val api: OrderApi
) : OrderRepository {

    override fun getOrders(restaurantId: String): Flow<List<Order>> = flow {
        val response = api.getOrders()
        emit(response.map { it.toDomain() })
    }

    override fun getOrderById(orderId: String): Flow<Order?> = flow {
        try {
            val order = api.getOrderById(orderId).toDomain()
            emit(order)
        } catch (e: Exception) {
            emit(null)
        }
    }

    override fun getActiveOrders(): Flow<List<Order>> = flow {
        try {
            val orders = api.getActiveOrders().map { it.toDomain() }
            emit(orders)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    override fun getOrderHistory(): Flow<List<Order>> = flow {
        val response = api.getOrderHistory()
        emit(response.map { it.toDomain() })
    }

    override suspend fun placeOrder(order: Order): Result<Order> = runCatching {
        api.placeOrder(OrderDto.fromDomain(order)).toDomain()
    }

    override suspend fun cancelOrder(orderId: String): Result<Unit> = runCatching {
        api.cancelOrder(orderId)
    }

    override suspend fun updateOrderStatus(orderId: String, status: OrderStatus): Result<Unit> = runCatching {
        api.updateOrderStatus(orderId, status.name)
    }

    override suspend fun trackOrder(orderId: String): Flow<Order> = flow {
        try {
            val order = api.trackOrder(orderId).toDomain()
            emit(order)
        } catch (e: Exception) {
            // Handle error
        }
    }

    override suspend fun rateOrder(orderId: String, rating: Double, feedback: String?): Result<Unit> = runCatching {
        api.rateOrder(orderId, rating.toInt(), feedback)
    }
}
