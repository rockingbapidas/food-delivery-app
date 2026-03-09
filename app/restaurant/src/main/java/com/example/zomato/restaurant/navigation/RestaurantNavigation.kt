package com.example.zomato.restaurant.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.zomato.restaurant.ui.screens.menu.MenuManagementScreen
import com.example.zomato.restaurant.ui.screens.order.OrderManagementScreen

sealed class RestaurantScreen(val route: String) {
    object Orders : RestaurantScreen("orders")
    object Menu : RestaurantScreen("menu")
    object Analytics : RestaurantScreen("analytics")
}

@Composable
fun RestaurantNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = RestaurantScreen.Orders.route
    ) {
        composable(RestaurantScreen.Orders.route) {
            OrderManagementScreen(
                onBackClick = { navController.popBackStack() },
                onNavigateToMenu = { navController.navigate(RestaurantScreen.Menu.route) },
                onNavigateToAnalytics = { navController.navigate(RestaurantScreen.Analytics.route) }
            )
        }
        composable(RestaurantScreen.Menu.route) {
            MenuManagementScreen(
                onNavigateToOrders = { navController.navigate(RestaurantScreen.Orders.route) },
                onNavigateToAnalytics = { navController.navigate(RestaurantScreen.Analytics.route) },
                onBackClick = {TODO()},
                onAddItemClick = { TODO() },
                onEditItemClick = { TODO() }
            )
        }
        composable(RestaurantScreen.Analytics.route) {
            // AnalyticsScreen()
        }
    }
} 