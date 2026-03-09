package com.example.zomato.delivery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.zomato.delivery.ui.screens.delivery.DeliveryScreen
import com.example.zomato.delivery.ui.screens.earnings.EarningsScreen
import com.example.zomato.delivery.ui.screens.profile.ProfileScreen

sealed class DeliveryScreenRoute(val route: String) {
    object Home : DeliveryScreenRoute("home")
    object Earnings : DeliveryScreenRoute("earnings")
    object Profile : DeliveryScreenRoute("profile")
    object OrderDetails : DeliveryScreenRoute("order/{orderId}") {
        fun createRoute(orderId: String) = "order/$orderId"
    }
}

@Composable
fun DeliveryNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = DeliveryScreenRoute.Home.route) {
        composable(DeliveryScreenRoute.Home.route) {
            DeliveryScreen(
                onNavigateToOrder = { orderId ->
                    navController.navigate(DeliveryScreenRoute.OrderDetails.createRoute(orderId))
                },
                onEarningsClick = {
                    navController.navigate(DeliveryScreenRoute.Earnings.route)
                },
                onProfileClick = {
                    navController.navigate(DeliveryScreenRoute.Profile.route)
                }
            )
        }
        composable(DeliveryScreenRoute.Earnings.route) {
            EarningsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(DeliveryScreenRoute.Profile.route) {
            ProfileScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(DeliveryScreenRoute.OrderDetails.route) {
            // OrderDetailsScreen()
        }
    }
} 