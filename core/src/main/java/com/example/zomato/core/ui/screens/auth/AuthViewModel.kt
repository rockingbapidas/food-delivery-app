package com.example.zomato.core.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zomato.domain.model.User
import com.example.zomato.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthState(
    val isAuthenticated: Boolean = false,
    val error: String? = null,
    val currentUser: User? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.isAuthenticated.collect { isAuthenticated ->
                _authState.value = _authState.value.copy(isAuthenticated = isAuthenticated)
            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password)
                .onSuccess {
                    _authState.value = _authState.value.copy(
                        isAuthenticated = true,
                        error = null
                    )
                }
                .onFailure {
                    _authState.value = _authState.value.copy(
                        error = it.message ?: "Authentication failed"
                    )
                }
        }
    }

    fun signUp(
        email: String,
        password: String,
        name: String,
        phone: String,
        role: String
    ) {
        viewModelScope.launch {
            authRepository.register(email, password, name, phone)
                .onSuccess {
                    _authState.value = _authState.value.copy(
                        isAuthenticated = true,
                        error = null
                    )
                }
                .onFailure {
                    _authState.value = _authState.value.copy(
                        error = it.message ?: "Registration failed"
                    )
                }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authRepository.logout()
            _authState.value = _authState.value.copy(
                isAuthenticated = false,
                error = null
            )
        }
    }
} 