package com.example.zomato.customer.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zomato.domain.model.CartItem
import com.example.zomato.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CartUiState {
    object Loading : CartUiState()
    data class Success(val cartItems: List<CartItem>) : CartUiState()
    data class Error(val message: String) : CartUiState()
}

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CartUiState>(CartUiState.Loading)
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    init {
        loadCartItems()
    }

    private fun loadCartItems() {
        viewModelScope.launch {
            cartRepository.getCartItems()
                .catch { e ->
                    _uiState.value = CartUiState.Error(e.message ?: "Error loading cart items")
                }
                .collect { cartItems ->
                    _uiState.value = CartUiState.Success(cartItems)
                }
        }
    }

    fun updateQuantity(menuItemId: String, quantity: Int) {
        viewModelScope.launch {
            try {
                if (quantity > 0) {
                    cartRepository.updateQuantity(menuItemId, quantity)
                } else {
                    cartRepository.removeFromCart(menuItemId)
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun calculateTotal(): Double {
        return when (val state = uiState.value) {
            is CartUiState.Success -> {
                state.cartItems.sumOf { it.menuItem.price * it.quantity }
            }
            else -> 0.0
        }
    }

    fun placeOrder(onOrderPlaced: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val orderId = cartRepository.placeOrder()
                onOrderPlaced(orderId.getOrNull() ?: throw Exception("Failed to place order"))
            } catch (e: Exception) {
                _uiState.value = CartUiState.Error(e.message ?: "Error placing order")
            }
        }
    }
} 