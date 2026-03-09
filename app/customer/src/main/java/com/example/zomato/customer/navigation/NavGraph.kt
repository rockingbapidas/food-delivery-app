package com.example.zomato.customer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.zomato.customer.ui.screens.cart.CartScreen
import com.example.zomato.customer.ui.screens.home.HomeScreen
import com.example.zomato.customer.ui.screens.order.OrderTrackingScreen
import com.example.zomato.customer.ui.screens.profile.ProfileScreen
import com.example.zomato.customer.ui.screens.restaurant.RestaurantDetailScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object RestaurantDetail : Screen("restaurant/{restaurantId}") {
        fun createRoute(restaurantId: String) = "restaurant/$restaurantId"
    }
    object Cart : Screen("cart")
    object OrderTracking : Screen("order/{orderId}") {
        fun createRoute(orderId: String) = "order/$orderId"
    }
    object Profile : Screen("profile")
}

@Composable
fun CustomerNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onRestaurantClick = { restaurantId ->
                    navController.navigate(Screen.RestaurantDetail.createRoute(restaurantId))
                },
                onCartClick = {
                    navController.navigate(Screen.Cart.route)
                },
                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                },
                onSearchClick = {},
                onOrdersClick = {}
            )
        }

        composable(Screen.RestaurantDetail.route) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: return@composable
            RestaurantDetailScreen(
                restaurantId = restaurantId,
                onBackClick = { navController.popBackStack() },
                onAddToCart = { navController.navigate(Screen.Cart.route) }
            )
        }

        composable(Screen.Cart.route) {
            CartScreen(
                onBackClick = { navController.popBackStack() },
                onPlaceOrder = { orderId ->
                    navController.navigate(Screen.OrderTracking.createRoute(orderId)) {
                        popUpTo(Screen.Home.route)
                    }
                }
            )
        }

        composable(Screen.OrderTracking.route) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: return@composable
            OrderTrackingScreen(
                orderId = orderId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onEditProfile = { TODO() },
                onAddresses = { TODO() },
                onPaymentMethods = { TODO() },
                onSettings = { TODO() }
            )
        }
    }
} 