package com.example.zomato.delivery.ui.screens.delivery

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.zomato.domain.model.Order
import com.example.zomato.domain.model.OrderStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryScreen(
    onNavigateToOrder: (String) -> Unit,
    onEarningsClick: () -> Unit,
    onProfileClick: () -> Unit,
    viewModel: DeliveryViewModel = hiltViewModel()
) {
    val orders by viewModel.orders.collectAsState()
    var isOnline by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Delivery") },
                actions = {
                    IconButton(onClick = onEarningsClick) {
                        Icon(Icons.Default.Person, contentDescription = "Earnings")
                    }
                    IconButton(onClick = onProfileClick) {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Online/Offline Toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isOnline) "You're Online" else "You're Offline",
                    style = MaterialTheme.typography.titleMedium
                )
                Switch(
                    checked = isOnline,
                    onCheckedChange = { isOnline = it }
                )
            }

            if (isOnline) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(orders) { order ->
                        OrderCard(
                            order = order,
                            onAcceptClick = { viewModel.acceptOrder(order.id) },
                            onRejectClick = { viewModel.rejectOrder(order.id) },
                            onCompleteClick = { viewModel.completeOrder(order.id) }
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "You're currently offline",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderCard(
    order: Order,
    onAcceptClick: () -> Unit,
    onRejectClick: () -> Unit,
    onCompleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Order #${order.id}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "From: ${order.restaurantName}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "To: ${order.deliveryAddress}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Amount: ₹${order.totalAmount}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                when (order.status) {
                    OrderStatus.PENDING -> {
                        Button(onClick = onAcceptClick) {
                            Text("Accept")
                        }
                        OutlinedButton(onClick = onRejectClick) {
                            Text("Reject")
                        }
                    }
                    OrderStatus.ACCEPTED -> {
                        Button(
                            onClick = onCompleteClick,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Mark as Delivered")
                        }
                    }
                    else -> {
                        Text(
                            text = "Status: ${order.status}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
} 