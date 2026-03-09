package com.example.zomato.restaurant.ui.screens.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zomato.domain.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AddEditMenuItemViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {
    // This ViewModel will handle the logic for adding and editing menu items.
    // It will interact with the MenuRepository to perform CRUD operations on menu items.
    // You can implement methods to add, update, and delete menu items here.

    private val _menuItems = menuRepository.getMenuItems("") // Assuming MenuRepository has a method to get menu items
    val categories: StateFlow<List<String>> = _menuItems.map { it.map { it.name } }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}