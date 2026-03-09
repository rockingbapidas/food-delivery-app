package com.example.zomato.delivery.ui.screens.earnings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zomato.domain.model.Order
import com.example.zomato.domain.model.OrderStatus
import com.example.zomato.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class Earning(
    val orderId: String,
    val amount: Double,
    val date: String
)

@HiltViewModel
class EarningsViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _earnings = MutableStateFlow<List<Earning>>(emptyList())
    val earnings: StateFlow<List<Earning>> = _earnings.asStateFlow()

    val totalEarnings: StateFlow<Double> = earnings.map { earnings ->
        earnings.sumOf { it.amount }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0.0
    )

    val totalDeliveries: StateFlow<Int> = earnings.map { earnings ->
        earnings.size
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    init {
        loadEarnings()
    }

    private fun loadEarnings() {
        orderRepository.getOrders("")
            .map { orders ->
                orders.filter { it.status == OrderStatus.DELIVERED }
                    .map { order ->
                        Earning(
                            orderId = order.id,
                            amount = order.deliveryFee,
                            date = order.deliveryDate ?: "N/A"
                        )
                    }
            }
            .onEach { earnings ->
                _earnings.value = earnings
            }
            .launchIn(viewModelScope)
    }
} 