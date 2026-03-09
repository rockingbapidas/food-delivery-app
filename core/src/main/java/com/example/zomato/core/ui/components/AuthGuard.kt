package com.example.zomato.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.zomato.core.ui.screens.auth.AuthScreen
import com.example.zomato.core.ui.screens.auth.AuthViewModel
import com.example.zomato.domain.model.UserRole

@Composable
fun AuthGuard(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
    requiredRole: UserRole? = null,
    content: @Composable () -> Unit
) {
    val authState by viewModel.authState.collectAsState()

    LaunchedEffect(authState.isAuthenticated) {
        if (!authState.isAuthenticated) {
            navController.navigate("auth") {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        } else if (requiredRole != null && authState.currentUser?.role != requiredRole) {
            navController.navigate("unauthorized") {
                popUpTo(navController.graph.startDestinationId)
            }
        }
    }

    if (!authState.isAuthenticated) {
        AuthScreen(
            onAuthSuccess = {
                navController.navigate("auth") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            },
            viewModel = viewModel
        )
    } else if (requiredRole == null || authState.currentUser?.role == requiredRole) {
        content()
    }
} 