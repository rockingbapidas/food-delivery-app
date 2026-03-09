package com.example.zomato.customer.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zomato.domain.model.Restaurant
import com.example.zomato.domain.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val restaurants: List<Restaurant>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadRestaurants()
    }

    private fun loadRestaurants() {
        viewModelScope.launch {
            restaurantRepository.getRestaurants()
                .catch { e ->
                    _uiState.value = HomeUiState.Error(e.message ?: "Unknown error occurred")
                }
                .collect { restaurants ->
                    _uiState.value = HomeUiState.Success(restaurants)
                }
        }
    }

    fun searchRestaurants(query: String) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val restaurants = if (query.isBlank()) {
                    restaurantRepository.getRestaurants().first()
                } else {
                    restaurantRepository.searchRestaurants(query).first()
                }
                _uiState.value = HomeUiState.Success(restaurants)
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Error searching restaurants")
            }
        }
    }
} 