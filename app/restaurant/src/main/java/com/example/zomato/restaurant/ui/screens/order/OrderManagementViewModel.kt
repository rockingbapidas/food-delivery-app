package com.example.zomato.restaurant.ui.screens.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zomato.domain.model.Order
import com.example.zomato.domain.model.OrderStatus
import com.example.zomato.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderManagementViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()

    init {
        loadOrders()
    }

    private fun loadOrders() {
        viewModelScope.launch {
            // TODO: Replace with actual restaurant ID from session
            val restaurantId = "current_restaurant_id"
            orderRepository.getOrders(restaurantId).collect { orders ->
                _orders.value = orders
            }
        }
    }

    fun acceptOrder(orderId: String) {
        viewModelScope.launch {
            // TODO: Replace with actual restaurant ID from session
            val restaurantId = "current_restaurant_id"
            orderRepository.updateOrderStatus(orderId, OrderStatus.ACCEPTED)
        }
    }

    fun rejectOrder(orderId: String) {
        viewModelScope.launch {
            // TODO: Replace with actual restaurant ID from session
            val restaurantId = "current_restaurant_id"
            orderRepository.updateOrderStatus(orderId, OrderStatus.CANCELLED)
        }
    }

    fun markOrderAsReady(orderId: String) {
        viewModelScope.launch {
            // TODO: Replace with actual restaurant ID from session
            val restaurantId = "current_restaurant_id"
            orderRepository.updateOrderStatus(orderId, OrderStatus.READY_FOR_PICKUP)
        }
    }
} 