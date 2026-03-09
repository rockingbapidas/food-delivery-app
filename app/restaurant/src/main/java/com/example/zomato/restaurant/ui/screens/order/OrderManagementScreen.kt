package com.example.zomato.restaurant.ui.screens.order

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun OrderManagementScreen(
    onBackClick: () -> Unit,
    onNavigateToMenu: () -> Unit,
    onNavigateToAnalytics: () -> Unit,
    viewModel: OrderManagementViewModel = hiltViewModel()
) {
    val orders by viewModel.orders.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Order Management") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(orders) { order ->
                OrderCard(
                    order = order,
                    onAcceptClick = { viewModel.acceptOrder(order.id) },
                    onRejectClick = { viewModel.rejectOrder(order.id) },
                    onReadyClick = { viewModel.markOrderAsReady(order.id) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OrderCard(
    order: Order,
    onAcceptClick: () -> Unit,
    onRejectClick: () -> Unit,
    onReadyClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
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
            Text(
                text = "Status: ${order.status}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Total: ₹${order.totalAmount}",
                style = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onAcceptClick,
                    enabled = order.status == OrderStatus.PENDING
                ) {
                    Text("Accept")
                }
                Button(
                    onClick = onRejectClick,
                    enabled = order.status == OrderStatus.PENDING
                ) {
                    Text("Reject")
                }
                Button(
                    onClick = onReadyClick,
                    enabled = order.status == OrderStatus.ACCEPTED
                ) {
                    Text("Mark as Ready")
                }
            }
        }
    }
} 