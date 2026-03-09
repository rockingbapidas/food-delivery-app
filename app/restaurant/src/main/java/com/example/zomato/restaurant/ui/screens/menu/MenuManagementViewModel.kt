package com.example.zomato.restaurant.ui.screens.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zomato.domain.model.MenuItem
import com.example.zomato.domain.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuManagementViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val _menuItems = MutableStateFlow<List<MenuItem>>(emptyList())
    val menuItems: StateFlow<List<MenuItem>> = _menuItems.asStateFlow()

    val categories: StateFlow<List<String>> = _menuItems
        .map { items -> items.map { it.category }.distinct().sorted() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        loadMenuItems()
    }

    private fun loadMenuItems() {
        viewModelScope.launch {
            // TODO: Get restaurant ID from user session
            val restaurantId = "current_restaurant_id"
            menuRepository.getMenuItems(restaurantId).collect { items ->
                _menuItems.value = items
            }
        }
    }

    fun refreshMenuItems() {
        viewModelScope.launch {
            // TODO: Get restaurant ID from user session
            val restaurantId = "current_restaurant_id"
            menuRepository.refreshMenuItems(restaurantId)
        }
    }

    suspend fun addMenuItem(menuItem: MenuItem) {
        // TODO: Replace with actual restaurant ID from session
        val restaurantId = "current_restaurant_id"
        menuRepository.addMenuItem(restaurantId, menuItem)
        refreshMenuItems()
    }

    suspend fun updateMenuItem(menuItem: MenuItem) {
        // TODO: Replace with actual restaurant ID from session
        val restaurantId = "current_restaurant_id"
        menuRepository.updateMenuItem(restaurantId, menuItem)
        refreshMenuItems()
    }

    suspend fun deleteMenuItem(menuItemId: String) {
        // TODO: Replace with actual restaurant ID from session
        val restaurantId = "current_restaurant_id"
        menuRepository.deleteMenuItem(restaurantId, menuItemId)
        refreshMenuItems()
    }
} 