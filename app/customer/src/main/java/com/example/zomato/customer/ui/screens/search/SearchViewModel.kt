package com.example.zomato.customer.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zomato.domain.model.Restaurant
import com.example.zomato.domain.usecase.SearchRestaurantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRestaurantsUseCase: SearchRestaurantsUseCase
) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<Restaurant>>(emptyList())
    val searchResults: StateFlow<List<Restaurant>> = _searchResults.asStateFlow()

    fun search(query: String) {
        viewModelScope.launch {
            searchRestaurantsUseCase(query).collect { restaurants ->
                _searchResults.value = restaurants
            }
        }
    }
} 