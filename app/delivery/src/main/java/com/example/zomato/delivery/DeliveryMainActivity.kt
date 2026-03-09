package com.example.zomato.delivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.zomato.delivery.navigation.DeliveryNavigation
import com.example.zomato.delivery.ui.theme.ZomatoDeliverTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeliveryMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZomatoDeliverTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    DeliveryNavigation(navController = navController)
                }
            }
        }
    }
} 