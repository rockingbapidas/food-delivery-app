package com.example.zomato.customer.ui.screens.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zomato.domain.model.Order
import com.example.zomato.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class OrderTrackingUiState {
    object Loading : OrderTrackingUiState()
    data class Success(val order: Order) : OrderTrackingUiState()
    data class Error(val message: String) : OrderTrackingUiState()
}

@HiltViewModel
class OrderTrackingViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<OrderTrackingUiState>(OrderTrackingUiState.Loading)
    val uiState: StateFlow<OrderTrackingUiState> = _uiState.asStateFlow()

    fun loadOrderDetails(orderId: String) {
        viewModelScope.launch {
            _uiState.value = OrderTrackingUiState.Loading
            try {
                orderRepository.getOrderById(orderId)
                    .catch { e ->
                        _uiState.value = OrderTrackingUiState.Error(e.message ?: "Error loading order details")
                    }
                    .collect { order ->
                        order?.let { _uiState.value = OrderTrackingUiState.Success(it) }
                    }
            } catch (e: Exception) {
                _uiState.value = OrderTrackingUiState.Error(e.message ?: "Error loading order details")
            }
        }
    }
} 