package com.example.zomato.customer.ui.screens.order

import androidx.lifecycle.ViewModel
import com.example.zomato.domain.model.Order
import com.example.zomato.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OrderViewModel@Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    val _orders = MutableStateFlow<List<Order>>(listOf())
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()
}