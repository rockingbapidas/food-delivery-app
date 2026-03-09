package com.example.zomato.customer.ui.screens.profile

import androidx.lifecycle.ViewModel
import com.example.zomato.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    init {
        // TODO: Load user data from repository
        _user.value = User(
            id = "1",
            name = "John Doe",
            email = "john.doe@example.com",
            phoneNumber = "+91 1234567890",
            addresses = TODO(),
            defaultAddressId = TODO(),
            role = TODO()
        )
    }

    fun logout() {
        // TODO: Implement logout
    }
} 