package com.example.zomato.restaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.zomato.restaurant.navigation.RestaurantNavigation
import com.example.zomato.restaurant.ui.theme.ZomatoRestaurantTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZomatoRestaurantTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    RestaurantNavigation(navController = navController)
                }
            }
        }
    }
} 