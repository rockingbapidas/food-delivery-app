package com.example.zomato.customer.ui.screens.order

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.zomato.domain.model.Order
import com.example.zomato.domain.model.OrderStatus

@Composable
fun OrderScreen(
    onOrderClick: (String) -> Unit,
    onTrackOrder: (String) -> Unit,
    viewModel: OrderViewModel = hiltViewModel()
) {
    var selectedTab by remember { mutableStateOf(0) }
    val orders by viewModel.orders.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabRow(selectedTabIndex = selectedTab) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("Active Orders") }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("Order History") }
            )
        }

        when (selectedTab) {
            0 -> OrderList(
                orders = orders.filter { it.status != OrderStatus.DELIVERED },
                onOrderClick = onOrderClick,
                onTrackOrder = onTrackOrder
            )
            1 -> OrderList(
                orders = orders.filter { it.status == OrderStatus.DELIVERED },
                onOrderClick = onOrderClick,
                onTrackOrder = onTrackOrder
            )
        }
    }
}

@Composable
private fun OrderList(
    orders: List<Order>,
    onOrderClick: (String) -> Unit,
    onTrackOrder: (String) -> Unit
) {
    if (orders.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No orders found")
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        orders.forEach { order ->
            OrderCard(
                order = order,
                onClick = { onOrderClick(order.id) },
                onTrackClick = { onTrackOrder(order.id) }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun OrderCard(
    order: Order,
    onClick: () -> Unit,
    onTrackClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Order #${order.id}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = order.status.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = when (order.status) {
                        OrderStatus.PREPARING -> MaterialTheme.colorScheme.primary
                        OrderStatus.OUT_FOR_DELIVERY -> MaterialTheme.colorScheme.tertiary
                        OrderStatus.DELIVERED -> MaterialTheme.colorScheme.secondary
                        else -> MaterialTheme.colorScheme.onSurface
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = order.restaurantName,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "₹${order.totalAmount}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )

            if (order.status == OrderStatus.OUT_FOR_DELIVERY) {
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onTrackClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Track Order")
                }
            }
        }
    }
} 