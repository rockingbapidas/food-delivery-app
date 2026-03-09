package com.example.zomato.customer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.zomato.customer.ui.screens.cart.CartScreen
import com.example.zomato.customer.ui.screens.home.HomeScreen
import com.example.zomato.customer.ui.screens.order.OrderScreen
import com.example.zomato.customer.ui.screens.order.OrderTrackingScreen
import com.example.zomato.customer.ui.screens.restaurant.RestaurantDetailScreen
import com.example.zomato.customer.ui.screens.search.SearchScreen

sealed class CustomerScreen(val route: String) {
    object Home : CustomerScreen("home")
    object Orders : CustomerScreen("orders")
    object OrderDetail : CustomerScreen("order/{orderId}") {
        fun createRoute(orderId: String) = "order/$orderId"
    }
    object OrderTracking : CustomerScreen("order/{orderId}/track") {
        fun createRoute(orderId: String) = "order/$orderId/track"
    }
    object Profile : CustomerScreen("profile")
    object Settings : CustomerScreen("settings")
    object RestaurantDetail : CustomerScreen("restaurant/{restaurantId}") {
        fun createRoute(restaurantId: String) = "restaurant/$restaurantId"
    }
    object Search : CustomerScreen("search")
    object Cart : CustomerScreen("cart")
}

@Composable
fun CustomerNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = CustomerScreen.Home.route
    ) {
        composable(CustomerScreen.Home.route) {
            HomeScreen(
                onRestaurantClick = { restaurantId ->
                    navController.navigate(CustomerScreen.RestaurantDetail.createRoute(restaurantId))
                },
                onSearchClick = {
                    navController.navigate(CustomerScreen.Search.route)
                },
                onCartClick = {
                    navController.navigate(CustomerScreen.Cart.route)
                },
                onProfileClick = {
                    navController.navigate(CustomerScreen.Profile.route)
                },
                onOrdersClick = {
                    navController.navigate(CustomerScreen.Orders.route)
                }
            )
        }

        composable(CustomerScreen.Orders.route) {
            OrderScreen(
                onOrderClick = { orderId ->
                    navController.navigate(CustomerScreen.OrderDetail.createRoute(orderId))
                },
                onTrackOrder = { orderId ->
                    navController.navigate(CustomerScreen.OrderTracking.createRoute(orderId))
                }
            )
        }

        composable(
            route = CustomerScreen.OrderDetail.route,
            arguments = listOf(
                navArgument("orderId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: return@composable
            /*OrderDetailScreen(
                orderId = orderId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onTrackOrder = { orderId ->
                    navController.navigate(CustomerScreen.OrderTracking.createRoute(orderId))
                }
            )*/
        }

        composable(
            route = CustomerScreen.OrderTracking.route,
            arguments = listOf(
                navArgument("orderId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: return@composable
            OrderTrackingScreen(
                orderId = orderId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(CustomerScreen.RestaurantDetail.route) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: return@composable
            RestaurantDetailScreen(
                restaurantId = restaurantId,
                onBackClick = { navController.popBackStack() },
                onAddToCart = { /* TODO: Handle add to cart */ }
            )
        }

        composable(CustomerScreen.Search.route) {
            SearchScreen(
                onBackClick = { navController.popBackStack() },
                onRestaurantClick = { restaurantId ->
                    navController.navigate(CustomerScreen.RestaurantDetail.createRoute(restaurantId))
                }
            )
        }

        composable(CustomerScreen.Cart.route) {
            CartScreen(
                onBackClick = { navController.popBackStack() },
                onPlaceOrder = { /* TODO: Navigate to checkout */ }
            )
        }
    }
} 