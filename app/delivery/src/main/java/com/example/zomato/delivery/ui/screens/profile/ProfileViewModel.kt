package com.example.zomato.delivery.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zomato.domain.model.OrderStatus
import com.example.zomato.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class DeliveryProfile(
    val name: String,
    val phone: String,
    val email: String,
    val vehicle: String,
    val totalDeliveries: Int,
    val rating: Double
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _profile = MutableStateFlow(
        DeliveryProfile(
            name = "John Doe",
            phone = "+91 9876543210",
            email = "john.doe@example.com",
            vehicle = "Bike",
            totalDeliveries = 0,
            rating = 0.0
        )
    )
    val profile: StateFlow<DeliveryProfile> = _profile.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        orderRepository.getOrders("")
            .map { orders ->
                val completedOrders = orders.count { it.status == OrderStatus.DELIVERED }
                val totalRating = orders
                    .filter { it.status == OrderStatus.DELIVERED }
                    .sumOf { it.rating ?: 0.0 }
                val averageRating = if (completedOrders > 0) {
                    totalRating / completedOrders
                } else {
                    0.0
                }

                _profile.value.copy(
                    totalDeliveries = completedOrders,
                    rating = averageRating
                )
            }
            .onEach { updatedProfile ->
                _profile.value = updatedProfile
            }
            .launchIn(viewModelScope)
    }
} 