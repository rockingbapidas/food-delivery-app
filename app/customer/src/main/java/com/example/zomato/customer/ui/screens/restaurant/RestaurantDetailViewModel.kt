package com.example.zomato.customer.ui.screens.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zomato.domain.model.MenuItem
import com.example.zomato.domain.model.Restaurant
import com.example.zomato.domain.repository.CartRepository
import com.example.zomato.domain.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RestaurantDetailUiState {
    object Loading : RestaurantDetailUiState()
    data class Success(val restaurant: Restaurant) : RestaurantDetailUiState()
    data class Error(val message: String) : RestaurantDetailUiState()
}

@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RestaurantDetailUiState>(RestaurantDetailUiState.Loading)
    val uiState: StateFlow<RestaurantDetailUiState> = _uiState.asStateFlow()

    fun loadRestaurantDetails(restaurantId: String) {
        viewModelScope.launch {
            _uiState.value = RestaurantDetailUiState.Loading
            try {
                val restaurant = restaurantRepository.getRestaurantById(restaurantId)
                _uiState.value = RestaurantDetailUiState.Success(restaurant.single())
            } catch (e: Exception) {
                _uiState.value = RestaurantDetailUiState.Error(e.message ?: "Error loading restaurant details")
            }
        }
    }

    fun addToCart(menuItem: MenuItem) {
        viewModelScope.launch {
            try {
                cartRepository.addToCart(menuItem.id)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
} 